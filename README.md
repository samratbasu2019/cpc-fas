# cpc-fas
POST:
http://localhost:8300/cpc/updateFas
{
    "name": "Dheeraj",
    "sex": "Male",
    "dob": "18/03/2013",
    "fasAddress": [
        {
            "address": "Bangalore",
            "landmark": "Konnankunte"
        },
        {
            "address": "Kolkata",
            "landmark": "Telipara"
        }
    ]
}
GET:
http://localhost:8300/cpc/getFASDataSet/{id}
