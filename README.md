# API Documentation

## Login

    endpoint: /api/v1/auth/login,
    method: POST,
    request Body : {
        userName: `String`,
        password: `String
    }

## Register

    endpoint: /api/v1/auth/register,
    method: POST,
    request Body : {
        userName: `String`,
        password: `String
    }

## Verify Token

    endpoint: /api/v1/verify,
    method: GET,
    headers: {
        Authorization: Bearer Token
    }

## Update Profile

    endpoint: /api/v1/user/{id},
    method: PUT,
    headers: {
        Authorization: Bearer Token
    },
    request Body : {
        oldUserName: `String`,
        newUserName: `String`,
        oldPassword: `String,
        newPassword: `String,
    }

## Create Item

    endpoint: /api/v1/item,
    method: POST,
    headers: {
        Authorization: Bearer Token
    },
    request Body : {
        name: `String`,
        description: `String`,
        categoryId: `Integer`,
        price: `Integer`,
        userId: `Integer`
    }

## Get All Items

    endpoint: /api/v1/items,
    method: GET,
    headers: {
        Authorization: Bearer Token
    }

## Get Item By ItemId

    endpoint: /api/v1/item/{id},
    method: GET,
    headers: {
        Authorization: Bearer Token
    }

## Get All Items By UserId

    endpoint: /api/v1/items/user/{userId},
    method: GET,
    headers: {
        Authorization: Bearer Token
    }

## Get All Items By CategoryId

    endpoint: /api/v1/items/category/{categoryId},
    method: GET,
    headers: {
        Authorization: Bearer Token
    }

## Update Item

    endpoint: /api/v1/item/{id},
    method: PUT,
    headers: {
        Authorization: Bearer Token
    },
    request Body : {
        name: `String`,
        description: `String`,
        categoryId: `Integer`,
        price: `Integer`,
        userId: `Integer`
    }

## Delete Item

    endpoint: /api/v1/item/{id},
    method: DELETE,
    headers: {
        Authorization: Bearer Token
    }

## Get All Categories

    endpoint: /api/v1/categories,
    method: GET,
    headers: {
        Authorization: Bearer Token
    }

## Get Category By Id

    endpoint: /api/v1/category/{id},
    method: GET,
    headers: {
        Authorization: Bearer Token
    }

## Get All Users

    endpoint: /api/v1/users,
    method: GET,
    headers: {
        Authorization: Bearer Token
    }

About project can be found [here](https://github.com/WilliamChang80/SEA-FE)
