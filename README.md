# Tech News App

This mobile application is designed for viewing a list of news articles and obtaining detailed information about each one. 
The app interacts with a [REST API](https://newsapi.org/) to fetch news data.

<img height="350" alt="Screenshot 2023-09-12 at 12 26 27" src="https://github.com/user-attachments/assets/1ab61dd5-6992-4021-9f29-2e5cac64bcea">
<img height="350" alt="Screenshot 2023-09-12 at 12 26 59" src="https://github.com/user-attachments/assets/9921a90e-9c94-4313-b4ee-1b0b46570091">

# Core Features

1. Main Screen with News List:
   - Display a list of news articles with titles, brief descriptions, and images.
   
2. Detailed News Information Screen:
   - Show complete information about the selected news article, including title, description, and image.

# Technologies

- Kotlin
- Jetpack Compose: For building the user interface.
- Coroutines: For handling asynchronous operations.
- ViewModel and Flow: For managing UI-related data in a lifecycle-conscious way.
- Retrofit: For interacting with the REST API.
- Hilt: For dependency injection.

# How to use

Please, insert your api key from newsapi.org to gradle.properties file

<img width="243" alt="Screenshot 2024-08-30 at 11 48 35" src="https://github.com/user-attachments/assets/c86cd114-8ad8-4693-a1ce-b37eb7639992">
