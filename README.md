## Evaluacion:Java


##REPOSITORIO GITHUB

 https://github.com/jordandiazdiaz/EvaluacionJava.git


##ENDPOINTS

## REGISTRAR USUARIO

/app/register
```json
 {
        "name": "Jordan Diaz Diaz",
        "email": "jordandiaz2016@gmail.com",
        "password": "Lampara1988",
        "phones": [
            {
                "number": "921484773",
                "citycode": "1",			
                "contrycode": "57"
            }
        ]
}
```
## LOGIN USUARIO

/app/login
```json
{
    "username": "jordandiaz2016@gmail.com",
    "password" : "Lampara1988"
}
```

## PROFILE USUARIO

/app/concrete/profile/53d46ddd-74cb-4f5d-9780-df8729f22b07

agregar en header 
X-auth-token : {TOKEN GENERADO}


