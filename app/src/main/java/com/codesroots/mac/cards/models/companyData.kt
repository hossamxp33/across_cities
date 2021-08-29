package com.codesroots.mac.cards.models



typealias CompanyData = List<CompanyDatum>



data class CompanyDatum (
    var id: String? = null,
    var name: String? = null,
    var src: String? = null,
    var sprice: String? = null,
    var rprice: String? = null,
    var detailimg:String? = null,
    var mount :Int?=  null

)
