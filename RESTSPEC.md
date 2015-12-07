# ITEMS

## /tasks

__POST__
new task with auto ID generation

## /{id}
**POST**
update task with ID <id> with new info

## /{id}/comment
**POST**
post comment to task with id

## /task/{id}/history
**POST**
post status change to task with id

_TODO_
/specification

# USERS

## /users
	curl -i -X POST -H "Content-type: application/json" --data @user.json localhost:8000/users

	{
	"firstname" : "cristianx",
	"password" : "cristian",
	"email" : "cristian@example.com",
	"lastname" : "m",
	"username" : "cristianx"
	}
	
**POST**
do validation
hash password with salt
???
store
return ok or not

## /specification
	curl -i localhost:8000/users/specification

reply with form
contains required fields, including password
specifies types

_TODO_
check fields for not null and type matching

## /token
	curl -i -H "Authorization: Basic Y3Jpc3RpYW46Y3Jpc3RpYW4=" localhost:8000/users/token

send username password in HTTP Authorization field base64 encoded;
generate token;
all subsequent requests would include the token in HTTP Authorization field - e.g.:

	curl -i -H "Authorization: Bearer $token" localhost:8000/tasks/2

## /permissions
	curl -i -H "Authorization: Bearer E6F6C051-F408-4055-9BD2-36254D67AE18" localhost:8000/users/permissions

returns permissions assoc. with Bearer token;

__POST__

	curl -i -X POST -H "Content-type: application/json" --data @perm.json -H "Authorization: Bearer E6F6C051-F408-4055-9BD2-36254D67AE18" localhost:8000/users/permissions

	{
	"memberid" : 5,
	"projectid" : 1,
	"position" : 2
	}
admin only
overwrites or creates permissions;

## /id/permissions
	curl -i -H "Authorization: Bearer E6F6C051-F408-4055-9BD2-36254D67AE18" localhost:8000/users/5/permissions

admin only
get permissions of user id

