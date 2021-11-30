package TheShoeBox.TheShoeBox.service.impl;

import TheShoeBox.TheShoeBox.model.entity.ShoeConditionEntity;
import TheShoeBox.TheShoeBox.model.entity.enums.ConditionEnum;
import TheShoeBox.TheShoeBox.repository.ShoeConditionRepository;
import TheShoeBox.TheShoeBox.service.ShoeConditionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ShoeConditionServiceImpl implements ShoeConditionService {

    private final ModelMapper modelMapper;
    private final ShoeConditionRepository shoeConditionRepository;

    public ShoeConditionServiceImpl(ModelMapper modelMapper, ShoeConditionRepository shoeConditionRepository) {
        this.modelMapper = modelMapper;
        this.shoeConditionRepository = shoeConditionRepository;
    }


    @Override
    public void initConditions() {
        if (shoeConditionRepository.count() > 0) {
            return;
        }

        Arrays.stream(ConditionEnum.values())
                .forEach(c -> {

                    ShoeConditionEntity conditionEntity = new ShoeConditionEntity();
                    conditionEntity.setName(c);

                    shoeConditionRepository.save(conditionEntity);
                });
    }
}
