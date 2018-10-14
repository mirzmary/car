package com.macadamian.car.service.helper;

import com.macadamian.car.persistence.brand.BrandRepository;
import com.macadamian.car.persistence.brand.entity.Brand;
import com.macadamian.car.persistence.color.ColorRepository;
import com.macadamian.car.persistence.color.entity.Color;
import com.macadamian.car.persistence.feature.FeatureRepository;
import com.macadamian.car.persistence.feature.entity.Feature;
import com.macadamian.car.persistence.featurevalue.FeatureValueRepository;
import com.macadamian.car.persistence.featurevalue.entity.FeatureValue;
import com.macadamian.car.persistence.model.ModelRepository;
import com.macadamian.car.persistence.model.entity.Model;
import com.macadamian.car.persistence.user.UserRepository;
import com.macadamian.car.persistence.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ServiceIntegrationTestHelper extends CommonTestHelper {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private FeatureValueRepository featureValueRepository;

    @Autowired
    private UserRepository userRepository;

    public User createAndPersistUser() {
        final User user = createUser();
        final User existingUser = userRepository.findByLogin(user.getLogin());
        if (existingUser != null) {
            return existingUser;
        } else {
            return userRepository.save(user);
        }
    }

    public Brand createAndPersistBrand() {
        final Brand brand = createBrand();
        return brandRepository.save(brand);
    }

    public Model createAndPersistModel() {
        final Model model = createModel();
        return modelRepository.save(model);
    }

    public Color createAndPersistColor() {
        final Color color = createColor();
        return colorRepository.save(color);
    }

    public List<Feature> createAndPersistFeatures() {
        final List<Feature> features = createFeatureList();
        return featureRepository.save(features);
    }

    public Feature createAndPersistTransmissionFeature() {
        final Feature feature = new Feature("Transmission", true);
        return featureRepository.save(feature);
    }

    public List<FeatureValue> createAndPersistFeatureValues(final Feature feature) {
        final List<FeatureValue> featureValues = createFeatureValues(feature);
        return featureValueRepository.save(featureValues);
    }
}
