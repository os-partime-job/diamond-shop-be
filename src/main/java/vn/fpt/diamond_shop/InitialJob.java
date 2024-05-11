package vn.fpt.diamond_shop;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vn.fpt.diamond_shop.constants.*;
import vn.fpt.diamond_shop.model.*;
import vn.fpt.diamond_shop.repository.*;

//@Component
@RequiredArgsConstructor
public class InitialJob implements CommandLineRunner {

    private final ClarityRepository clarityRepository;
    private final ColorRepository colorRepository;
    private final ConfigValueRepository configValueRepository;
    private final CutRepository cutRepository;
    private final PolishRepository polishRepository;
    private final ShapeRepository shapeRepository;


    @Override
    public void run(String... args) throws Exception {
        initClarity();
        initColor();
        initCut();
    }

    private void initClarity() {
        for (DiamondClarityEnum value : DiamondClarityEnum.values()) {
            clarityRepository.save(new Clarity(value));
        }
    }

    private void initColor() {
        for (DiamondColorEnum value : DiamondColorEnum.values()) {
            colorRepository.save(new Color(value));
        }
    }

    private void initCut() {
        for (int i = 0; i <= 10; i++) {
            cutRepository.save(new Cut(DiamondCutEnum.getDiamondCutEnum(i), i));
        }
    }


}
