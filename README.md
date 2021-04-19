# spring-bulletin-board

This is the final project for the Server (back-end) Development course in Haaga-Helia UAS.

The application provides an API for [a separate front-end part](https://github.com/keep1tstupid/react-bulletin-board-front), the endpoints are briefly described below.


#### In the project I am using:  
* *Jwt authentication*
* *PostgreSQL* to store data 
* *Google Cloud Storage* to store and access files (attachments)

<hr />

### API Documentation. 

<hr />

:page_facing_up: **Authentication:**

**POST "/api/auth/signin"** >> for login

Content-Type: application/json

```
    password: String
    username: String
```

<hr />

:page_facing_up:  **Items:**

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

```

```

**GET "/api/types"** >> get all item types, returns values from ItemTypes enum

Content-Type: application/json

`[String, String, ... ]`


**POST "/api/items"** >> add new item

```
    {
        attachmentFile: ""/{}
        author: String
        contactInfo: String
        description: String
        state: "IN_MODERATION"
        title: String
        type: "ADVERTISEMENT"/"COMPLAINT"/"NOTE"/"OTHER"
    }
```

**PUT "/api/items/{id}"** >> update existing item

Content-Type: application/json

```
    {
        attachmentFile: ""/file
        attachmentId: int
        contactInfo: String
        description: String
        id: int
        state: "IN_MODERATION"
        title: String
        type: "ADVERTISEMENT"/"COMPLAINT"/"NOTE"/"OTHER"
    }
```

**DELETE "/api/items/{id}"** >> delete item

<hr />

:page_facing_up:  **Attachments:**

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

<hr />

:page_facing_up:  **Users:**

**GET "/api/users"** >> get all users 

Content-Type: application/json

Available for users with "ROLE_ADMIN" role.
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
<hr />