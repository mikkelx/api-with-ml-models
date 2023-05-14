# api-with-ml-models
README

Opis projektu
Projekt polegał na stworzeniu systemu składającej się z dwóch obrażów dockerowych.
Pierwsza aplikacja napisana jest w pythonie, wczytuje wytrenowane przez nas modele 4 sieci neuronowej. Są to sieci splotowe wytrenowane na danych fashion-mnist, rozpoznające klasy ubrań. 
Druga aplikacja udostępnia prosty UI do podania zdjęcia. Zbiera później wyniki z predykcji i przedstawia je użytkownikowi. 

Wymagania systemowe
Do uruchomienia projektu potrzebny jest zainstalowany Docker oraz system operacyjny obsługujący Docker.

Uruchomienie aplikacji
Aby uruchomić aplikację, należy sklonować repozytorium oraz przejść do folderu projektu z plikiem docker-compose.yaml w terminalu. Następnie należy wywołać polecenie docker-compose up, które zbuduje i uruchomi obrazy dockerowe. Po zakończeniu procesu budowania i uruchomienia aplikacji, można otworzyć przeglądarkę internetową i wpisać adres http://localhost:8081/, aby skorzystać z aplikacji.

Opis interfejsu użytkownika
Interfejs użytkownika składa się z jednej strony, na której znajduje się formularz umożliwiający przesłanie obrazu z zestawu Fashion-MNIST do klasyfikacji. Po przesłaniu obrazu, aplikacja wyświetla wynik klasyfikacji w postaci prawdopodobieństwa przynależności do każdej z klas. 

Autorzy
Projekt został stworzony przez 
