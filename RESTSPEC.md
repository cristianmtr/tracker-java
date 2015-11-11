# ITEMS

/tasks

@POST
new task with auto ID generation

/<id>
@POST
update task with ID <id> with new info

Subresources

/<id>/comment
@POST
post comment to task with id

/task/<id>/history
POST
post status change to task with id

### TODO /specification

# USERS

/users

@POST
do validation
hash password with salt
???
store
return ok or not

/specification
reply with form
contains required fields, including password
specify types
### TODO specify mandatory

/token
send username password in HTTP Authorization field
generate token
```
SecureRandom random = new SecureRandom();
byte bytes[] = new byte[20];
random.nextBytes(bytes);
String token = bytes.toString();
```
all subsequent requests would include the token in HTTP Authorization field



