from flask import Flask, request, jsonify, send_file

import numpy as np
from tensorflow.keras.models import load_model
from tensorflow.keras.preprocessing import image
import json
from PIL import Image, ImageOps

app = Flask(__name__)

# ścieżka do pliku z modelem
MODEL_PATH_1 = 'cnn_model_0.h5'
MODEL_PATH_2 = 'cnn_model_1.h5'
MODEL_PATH_3 = 'cnn_model_2.h5'
STACKING_MODEL_PATH = 'stacking_model.h5'

# wczytanie modelu
model_1 = load_model(MODEL_PATH_1)
model_2 = load_model(MODEL_PATH_2)
model_3 = load_model(MODEL_PATH_3)
stacking_model = load_model(STACKING_MODEL_PATH)

# wymiary wejściowe obrazu
IMG_SIZE = (28, 28)

# lista etykiet klas
CLASS_LABELS = ['T-shirt/top', 'Trouser', 'Pullover', 'Dress', 'Coat', 'Sandal', 'Shirt', 'Sneaker', 'Bag', 'Ankle boot']


@app.route('/predict', methods=['POST'])
def predict():
    file = request.files['image']
    img = Image.open(file.stream)
    img = img.convert('L')  # konwersja na tryb kolorów L (luminancja)
    img = ImageOps.invert(img)  # uzyskanie negatywu
    resized_img = ImageOps.fit(img, (28, 28))

    # konwersja obrazu na tablicę numpy
    # img = image.load_img(image_file, target_size=IMG_SIZE)
    x = image.img_to_array(resized_img) / 255.
    x = np.expand_dims(x, axis=0)

    #Zapis obrazka do pliku
    img.save("lastPicture.png", "PNG")

    # predykcja na podstawie modelu 1
    y_pred_1 = model_1.predict(x)[0]
    class_idx_1 = np.argmax(y_pred_1)
    class_label_1 = CLASS_LABELS[class_idx_1]
    confidence_1 = y_pred_1[class_idx_1]

    # predykcja na podstawie modelu 2
    y_pred_2 = model_2.predict(x)[0]
    class_idx_2 = np.argmax(y_pred_2)
    class_label_2 = CLASS_LABELS[class_idx_2]
    confidence_2 = y_pred_2[class_idx_2]

    # predykcja na podstawie modelu 3
    y_pred_3 = model_3.predict(x)[0]
    class_idx_3 = np.argmax(y_pred_3)
    class_label_3 = CLASS_LABELS[class_idx_3]
    confidence_3 = y_pred_3[class_idx_3]


    #stack results
    predictions = np.stack([y_pred_1, y_pred_2, y_pred_3])
    predictions = predictions.reshape(1, 3, 10, 1)

    y_result = stacking_model.predict(predictions)[0]
    stacking_class_idx = np.argmax(y_result)
    stacking_class_label = CLASS_LABELS[stacking_class_idx]
    stacking_confidence = y_result[stacking_class_idx]


    # return jsonify(
    #     {'class' : stacking_class_label,
    #      'confidence' : float(stacking_confidence)}
    # )

    return jsonify(
        {'m1_class': class_label_1, 'm1_confidence': float(confidence_1),
        'm2_class': class_label_2, 'm2_confidence': float(confidence_2),
        'm3_class': class_label_3, 'm3_confidence': float(confidence_3),
        'stackingClass': stacking_class_label,'stackingConfidence': float(stacking_confidence)
         })

@app.route('/getLastPicture', methods=['GET'])
def getLastPicture():
    return send_file('lastPicture.png', mimetype='image/png')

if __name__ == '__main__':
    app.run()
