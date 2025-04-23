package ead.ca2.quotemagnet.data.favourites.model

    import ead.ca2.quotemagnet.domain.model.Quotation

    fun DatabaseQuotationDto.toDomain(): Quotation =
        Quotation(
            id = id,
            text = text,
            author = author
        )


    fun Quotation.toDatabaseDto() : DatabaseQuotationDto =
        DatabaseQuotationDto(
            id =id,
            text =text,
            author =author)

