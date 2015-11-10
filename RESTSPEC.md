POST /task - new task with auto ID generation
POST /task/<id> - update task with ID <id> with new info
POST /task/<id>/comment - post comment to task with id
POST /task/<id>/history - post status change to task with id

FORMAT:
{
"code":<code>,
"data": {}
}

NOTES:
- "data" will contain either one item, or a list of items, depending on endpoint;
- when the endpoint should only return ONE item, a 500 error will be returned instead;
- datetime fields will have the format: "Wed, 01 Oct 2014 09:36:21 GMT";

Authentication

/user/

/new
reply with form
contains required fields, including password
do validation
hash password with salt
???
store
return ok or not

/login
send username password in HTTP Authorization field
generate token
```
SecureRandom random = new SecureRandom();
byte bytes[] = new byte[20];
random.nextBytes(bytes);
String token = bytes.toString();
```
all subsequent requests would include the token in HTTP Authorization field

