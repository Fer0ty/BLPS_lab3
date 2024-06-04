package ru.artemiyandarina.lab3.services.mapping;

import org.springframework.stereotype.Service;
import ru.artemiyandarina.lab3.exceptions.UnknownCountryException;
import ru.artemiyandarina.lab3.models.Country;
import ru.artemiyandarina.lab3.models.Petition;
import ru.artemiyandarina.lab3.schemas.petition.PetitionBase;
import ru.artemiyandarina.lab3.schemas.petition.PetitionCreate;
import ru.artemiyandarina.lab3.schemas.petition.PetitionNotification;
import ru.artemiyandarina.lab3.schemas.petition.PetitionRead;

@Service
public class PetitionMapper {
    private final UserMapper userMapper;

    public PetitionMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    protected Petition mapPetitionBaseToEntity(PetitionBase schema, Petition entity) {
        entity.setTitle(schema.getTitle());
        entity.setDescription(schema.getDescription());
        return entity;
    }

    protected Petition mapPetitionBaseToEntity(PetitionBase schema) {
        return mapPetitionBaseToEntity(schema, new Petition());
    }

    public Petition mapPetitionCreateToEntity(PetitionCreate schema) {
        Petition newPetition = mapPetitionBaseToEntity(schema);
        try {
            newPetition.setCountry(Country.valueOf(schema.getCountry().toUpperCase()).toString());
        } catch (IllegalArgumentException e) {
            throw new UnknownCountryException();
        }
        return newPetition;
    }

    public PetitionNotification mapPetitionToPetitionNotification(Petition petition){
        PetitionNotification petitionNotification = new PetitionNotification();
        petitionNotification.setId(petition.getId());
        petitionNotification.setOwnerId(petition.getOwner().getId());
        petitionNotification.setApproveStatus(petition.getApproveStatus());
        return petitionNotification;
    }
    public PetitionRead mapEntityToPetitionRead(Petition entity) {
        PetitionRead schema = new PetitionRead();
        schema.setId(entity.getId());
        schema.setOwner(userMapper.mapEntityToUserRead(entity.getOwner()));
        schema.setTitle(entity.getTitle());
        schema.setDescription(entity.getDescription());
        schema.setCreationDate(entity.getCreationDate());
        schema.setCountry(entity.getCountry());
        return schema;
    }
}
