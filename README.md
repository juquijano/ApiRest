# ApiRest

>La API está desarrollada con Spark y Elasticsearch Rest High Level Client para almacenar items con ciertos key-values. 
>
>Está pensada para escuchar sobre localhost en el puerto 8080 y con HTTP/1.1
>
>Puede recibir métodos POST, GET, PUT y DELETE.
>

# POST
>Para ejecutar un post: 

`curl -X POST localhost:8080/items/<ID>\
-H "Content-Type: application/json" \
-d '<BODY>'`
>
>done el parámetro \<ID\> identifica al item y el \<BODY\> debe ser un json con los campos como el siguiente ejemplo:
>
    {

      "title": "Item de test - P Ofertar",

      "category_id": "MLA5529",

      "price": 10,

      "currency_id": "ARS",

      "available_quantity": 1,

      "buying_mode": "buy_it_now",

      "listing_type_id": "bronze",

      "condition": "old",

      "description": "Item:,  Ray-Ban WAYFARER Gloss Black RB2140 901  Model: RB2140. Size: 50mm. Name: WAYFARER. Color: Gloss Black. Includes Ray-Ban Carrying Case and Cleaning Cloth. New in Box",

      "video_id": "YOUTUBE_ID_HERE",

      "warranty": "12 months by Ray Ban",

      "pictures": [

          {

              "source": "http://upload.wikimedia.org/wikipedia/commons/f/fd/Ray_Ban_Original_Wayfarer.jpg"

          },

          {

              "source": "http://en.wikipedia.org/wiki/File:Teashades.gif"

          }

      ]
    }

>
>En caso de una inserción correcta, se debolverá un mensaje de "SUCCESS" con estatus 200.
>
  
# GET (un item)
>Para ejecutar un get sobre un item: 

  `curl -X POST localhost:8080/items/<ID>`

>Responderá con un mensaje de "SUCCESS" y la data del item en formato Json con status 200.

  
# GET (todos los items)
>Para ejecutar un get sobre todos los items: 

  `curl -X POST localhost:8080/items`

>Responderá con un mensaje de "SUCCESS" y la data de todos los items en formato Json con status 200.

>
# PUT (todos los items)
>Para ejecutar un get sobre todos los items: 

  `curl -X POST localhost:8080/items`

>Responderá con un mensaje de "SUCCESS" y la data de todos los items en formato Json con status 200.

>
>
>
>
>
>
>
>
>
