# О проекте
Проект представляет собой аналог асинхронной очереди. Приложение запускает Socket и ждет клиентов.
Клиенты могут быть двух типов: отправители (publisher), получатели (subscriber). В качестве клиента
будем использовать cURL. В качестве протокола будем использовать HTTP. 

# Description:
-This project is analogous to the asynchronous RabbitMQ queue.
-The application starts Socket and waits for clients.
-There are two types of clients: senders (publisher) and recipients (subscriber).
-HTTP is used as the protocol.
-There are two modes: queue, topic.
-No synchronization in the code. Implementation done by Executors and concurrent collections.