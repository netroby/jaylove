# Design

## API

### Response data format

Request API must be with POST method, you will got JSON response.

```json
{
    "code": 0,
    "msg": "I am msg", 
    "data": []
}
```

If the result was normal, code must = 0, else code = 1

msg contains the error message or normal message.

data contains the return data, if not , it will be null or `[]`