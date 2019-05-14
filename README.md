# nytimes-mvvm

Developed the project using MVVM architecture

Repository - Which serves as the communication bridge between the data and the rest of the app,
here it fetches data and synced to local database using room.

ViewModel - Which is a bridge between repository and view

Activity - Observes the data changes and renders UI

Used nytimes API using 'server sent event' for real time data with delay of max 30 seconds without poll  
