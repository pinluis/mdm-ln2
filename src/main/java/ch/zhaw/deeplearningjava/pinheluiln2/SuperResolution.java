package ch.zhaw.deeplearningjava.pinheluiln2;

import ai.djl.Application;
import ai.djl.ModelException;

import ai.djl.inference.Predictor;
import ai.djl.modality.cv.Image;

import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.training.util.ProgressBar;
import ai.djl.translate.TranslateException;



import java.io.IOException;

import java.util.Collections;
import java.util.List;

public final class SuperResolution {


    private SuperResolution() {}

    public static Image enhanceSingle(Image inputImage) throws IOException, ModelException, TranslateException {
        List<Image> inputImages = Collections.singletonList(inputImage);
        List<Image> enhancedImages = enhance(inputImages);
        return enhancedImages.get(0);
    }

    public static List<Image> enhance(List<Image> inputImages)
            throws IOException, ModelException, TranslateException {

        String modelUrl =
                "https://storage.googleapis.com/tfhub-modules/captain-pool/esrgan-tf2/1.tar.gz";
        Criteria<Image, Image> criteria =
                Criteria.builder()
                        .optApplication(Application.CV.IMAGE_ENHANCEMENT)
                        .setTypes(Image.class, Image.class)
                        .optModelUrls(modelUrl)
                        .optOption("Tags", "serve")
                        .optOption("SignatureDefKey", "serving_default")
                        .optTranslator(new SuperResolutionTranslator())
                        .optEngine("TensorFlow")
                        .optProgress(new ProgressBar())
                        .build();

        try (ZooModel<Image, Image> model = criteria.loadModel();
                Predictor<Image, Image> enhancer = model.newPredictor()) {
            return enhancer.batchPredict(inputImages);
        }
    }
}