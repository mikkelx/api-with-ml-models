# api-with-ml-models
README

The project involved creating a system consisting of two Docker containers. The first application is written in Python and loads four pre-trained neural network models. These are convolutional neural networks trained on fashion-mnist data, capable of recognizing clothing classes. The second application provides a simple user interface for uploading an image. It later collects the prediction results and presents them to the user.

System Requirements
To run the project, Docker needs to be installed, along with an operating system that supports Docker.

Running the Application
To run the application, clone the repository and navigate to the project folder containing the docker-compose.yaml file in the terminal. Then, execute the command "docker-compose up," which will build and run the Docker images. Once the building and running process is complete, you can open a web browser and enter the address http://localhost:8081/ to access the application.

User Interface Description
The user interface consists of a single page featuring a form that allows uploading an image from the Fashion-MNIST dataset for classification. After uploading the image, the application displays the classification result as the probability of belonging to each class.

Authors
The project was created by mikkelx, tzapadlinski
