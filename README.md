# TestChat

test app

In this version of the app cashing of date to DataBase is not implemented; Data stored in Standard collections in instance of DataManager

DataManager is created in ChatApp, which is extension of Application; instance of DataManager available by get DataManager() method of the Chat App.

IoManager is implemented. for testing on device some operations described by OpManager interface are available from drawer menu; -add user -send messages -change status -delete user

loopback sendMessage - > receiveMessage is not closed All View to data communication should be performed via its Presenters and visa-versa; (MVP)

Proposed Message object doesnt have ID field, to track its read/not_read status an extra table with hash from Message Object is used. Since Message objects should be stored in a predefined Structure. Extention of Message cant be used.

do not hesitate to text me to a.s.mukhin@gmail.com for any extra clarifications
