package com.jovian.p6_api_rest.model

import com.jovian.p6_api_rest.model.Amiibo

//modelo de datos. Recoge una lista de Amiibos
//De hecho el json solo contiene una lista de amiibos
data class AmiiboResponse(

    var amiibo: ArrayList<Amiibo>

)
