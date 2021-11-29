package TheShoeBox.TheShoeBox.service;

import TheShoeBox.TheShoeBox.model.bindng.ShoeBindingModel;
import TheShoeBox.TheShoeBox.model.service.ShoeServiceModel;

public interface ShoeService {
    ShoeServiceModel addShoe(ShoeBindingModel shoeServiceModel, String userIdentifier);
}
