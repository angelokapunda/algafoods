package com.algawords.algafoods.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

public class FileContentTypeValidator implements  ConstraintValidator<FileContentType, MultipartFile> {

    private List<String> allowedContentTypes;

    @Override
    public void initialize(FileContentType constraint) {
        this.allowedContentTypes = Arrays.asList(constraint.allowed());
    }

    @Override
    public boolean isValid(MultipartFile value,  ConstraintValidatorContext context) {
        return value == null || this.allowedContentTypes.contains(value.getContentType());
    }

}

