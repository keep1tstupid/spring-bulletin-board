# spring-bulletin-board

This is the final project for the Server (back-end) Development course in Haaga-Helia UAS.

The application provides an API for a separate front-end part (see another project), the endpoints are briefly described below.


#### In the project I am using:  
* *Jwt authentication*
* *PostgreSQL* to store data 
* *Google Cloud Storage* to store and access files (attachments)

#### API Documentation. 

Authentication:

**POST "/api/auth/signin"** >> for login

Items:

**GET "/api/items"** >> get all items

Content-Type: application/json
```
[
    {
        attachmentId: null/int
        author: String
        contactInfo: String
        description: String
        id: int
        state: "APPROVED"/"DECLINED"/"IN_MODERATION"
        title: String
        type: "ADVERTISEMENT"/"COMPLAINT"/"NOTE"/"OTHER"
    }, 
]
```

**GET "/api/items/{id}"** >> get item by id

**GET "/api/types"** >> get all item types, returns values from ItemTypes enum

Content-Type: application/json

`[String, String, ... ]`


**POST "/api/items"** >> add new item

**PUT "/api/items/{id}"** >> update existing item

**DELETE "/api/items/{id}"** >> delete item

Attachments: 

**POST "/api/upload"** >> add new file and link it to appropriate item

Content-Type: application/json
```
    file: (binary)
    id: int
```


**GET "/api/files"** >> get all files

```
[
    {
        id: int
        name: String
        size: int
        type: "image/jpeg"
        url: String
    }, 
]
```

**GET "/api/files/{id}"** >> get certain file

Users: 

**GET "/api/users"** >> get all users 
```
[
    {
        email: String
        id: int
        password: String
        role: "ROLE_ADMIN"/"ROLE_MODERATOR"/"ROLE_USER"
        username: String
    }, 
]
```

