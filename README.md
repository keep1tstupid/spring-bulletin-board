# spring-bulletin-board

The idea of Bulletin Board application is to provide a service for people who are living in the same buiding, where they can post notes, ads, complaints, etc. 

There are 3 roles in the app: user, moderator and administrator. 
Currently, I am working on email notifications.

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
        state: String
        title: String
        type: String
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

Request payload:

```
    {
        attachmentFile: ""/{}
        author: String
        contactInfo: String
        description: String
        state: String
        title: String
        type: String
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
        state: String
        title: String
        type: String
    }
```

**DELETE "/api/items/{id}"** >> delete item

<hr />

:page_facing_up:  **Attachments:**

**POST "/api/upload"** >> add new file and link it to appropriate item

Content-Type: application/json

Request payload:
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
        role: String
        username: String
    }, 
]
```

**GET "/api/roles"** >> get all roles

`[String, String, ... ]`


**POST "/api/users"** >> add new user

Content-Type: application/json

Request payload: 

```
{ 
    username: String
    password: String
    email: String
    role: String
}
```

<hr />