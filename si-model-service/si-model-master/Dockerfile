# Zainstaluj obraz bazowy Python
FROM python:3.10.11-slim-buster

# Ustaw katalog roboczy na /app
WORKDIR /app

# Skopiuj plik requirements.txt do katalogu roboczego
COPY requirements.txt .
COPY my_requirements.txt .

# Zainstaluj zależności z pliku requirements.txt
RUN pip install --no-cache-dir -r my_requirements.txt

# Skopiuj całą zawartość bieżącego katalogu do katalogu roboczego
COPY . .

# Ustaw zmienną środowiskową FLASK_APP na nazwę pliku, który zawiera aplikację Flask
ENV FLASK_APP=main.py
ENV FLASK_RUN_HOST=0.0.0.0

# Określamy port, na którym będzie działać nasza aplikacja
EXPOSE 5000

# Uruchom aplikację Flask na porcie 5000
CMD [ "flask", "run" , "--host=0.0.0.0"]
