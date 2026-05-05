# API Endpoints Documentation

##  Base URL

```
http://localhost:8085
```

---

## AUTH ENTITY

```
POST   /auth/register        -> Register new user
POST   /auth/login           -> Login & get JWT token
```

---

## USER ENTITY

```
GET    /api/users            -> Get all users
GET    /api/users/{id}       -> Get user by ID
POST   /api/users            -> Create user
PUT    /api/users/{id}       -> Update user
DELETE /api/users/{id}       -> Delete user
```

---

## TOPIC ENTITY

```
GET    /api/topics           -> Get all topics
GET    /api/topics/{id}      -> Get topic by ID
POST   /api/topics           -> Create topic
PUT    /api/topics/{id}      -> Update topic
DELETE /api/topics/{id}      -> Delete topic
```

---

## QUESTION ENTITY

```
GET    /api/questions                    -> Get all questions
GET    /api/questions/{id}               -> Get question by ID
GET    /api/questions/topic/{topicId}    -> Get questions by topic
POST   /api/questions                    -> Create question
PUT    /api/questions/{id}               -> Update question
DELETE /api/questions/{id}               -> Delete question
```

---

## USER PROGRESS ENTITY

```
GET    /api/progress                 -> Get all progress
GET    /api/progress/{id}            -> Get progress by ID
GET    /api/progress/user/{userId}   -> Get progress by user
POST   /api/progress                -> Create progress
PUT    /api/progress/{id}           -> Update progress
DELETE /api/progress/{id}           -> Delete progress
```

---

## USER ATTEMPT ENTITY

```
GET    /api/attempts                 -> Get all attempts
GET    /api/attempts/{id}            -> Get attempt by ID
GET    /api/attempts/user/{userId}   -> Get attempts by user
POST   /api/attempts                -> Create attempt
PUT    /api/attempts/{id}           -> Update attempt
DELETE /api/attempts/{id}           -> Delete attempt
```

---

## RECOMMENDATION ENTITY

```
GET    /api/recommendations                 -> Get all recommendations
GET    /api/recommendations/{id}            -> Get recommendation by ID
GET    /api/recommendations/user/{userId}   -> Get recommendations by user
POST   /api/recommendations                -> Create recommendation
PUT    /api/recommendations/{id}           -> Update recommendation
DELETE /api/recommendations/{id}           -> Delete recommendation
```
