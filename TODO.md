# TODO #

- links to actions
    would look like this:
    ```
    {
        links: {
            'action:comment': {
                'link': '/tasks/4/comments',
                'method': 'POST',
                }
            'self': {
                'link': '/tasks/4',
                'method': 'GET'
                }
        }
        data: {
          "id": 4,
          "projectid": 2,
          "itemparentid": null,
          "priority": 4,
          "context": null,
          "title": "test from web2222",
          "description": "test from web",
          "deadlinedate": "2015-10-03T00:00:00.000+0000",
          "memberid": 1,
          "authorid": null
          }

    } 
    ```   

- ETag standard:
    see pgs. 78 in `REST in Practice: Hypermedia and Systems Architecture`

    client attaches ETag string to ETag HTTP Header

    ```
    ETag: 'stringGoesHere'
    {
        lalal
    }
    ```

- logging 

- bearer token expiry date
