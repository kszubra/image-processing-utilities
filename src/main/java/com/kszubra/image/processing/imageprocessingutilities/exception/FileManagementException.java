package com.kszubra.image.processing.imageprocessingutilities.exception;

public class FileManagementException extends RuntimeException {

    public FileManagementException(String message) {
        super(message);
    }

    public static FileManagementException cantReadFile() {
        return new FileManagementException("Can't read file");
    }

    public static FileManagementException imageNotFound() {
        return new FileManagementException("Image not found");
    }
}
