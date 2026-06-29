package com.pratik;

import java.util.*;

public class DistributedFileStorageSimulator {

    static class FileObject {

        private String fileId;
        private String fileName;
        private long fileSize;
        private Date uploadTime;

        public FileObject(
                String fileId,
                String fileName,
                long fileSize) {

            this.fileId = fileId;
            this.fileName = fileName;
            this.fileSize = fileSize;
            this.uploadTime = new Date();
        }

        public long getFileSize() {
            return fileSize;
        }

        @Override
        public String toString() {
            return "File ID: " + fileId +
                    ", Name: " + fileName +
                    ", Size: " + fileSize + " MB" +
                    ", Uploaded: " + uploadTime;
        }
    }

    static class StorageService {

        private Map<String, FileObject> storage =
                new HashMap<>();

        private long usedSpace = 0;

        public void uploadFile(
                String fileId,
                String fileName,
                long size) {

            FileObject file =
                    new FileObject(
                            fileId,
                            fileName,
                            size);

            storage.put(fileId, file);
            usedSpace += size;

            System.out.println(
                    "Uploaded: " + fileName);
        }

        public void downloadFile(String fileId) {

            FileObject file = storage.get(fileId);

            if (file == null) {
                System.out.println("File not found");
                return;
            }

            System.out.println(
                    "Downloading -> " + file);
        }

        public void deleteFile(String fileId) {

            FileObject file =
                    storage.remove(fileId);

            if (file != null) {
                usedSpace -= file.getFileSize();
                System.out.println("File deleted");
            }
        }

        public void showStorageStats() {

            System.out.println(
                    "\nStorage Used: "
                            + usedSpace + " MB");

            System.out.println(
                    "Total Files: "
                            + storage.size());
        }
    }

    public static void main(String[] args) {

        StorageService service =
                new StorageService();

        service.uploadFile(
                "F1",
                "Resume.pdf",
                10);

        service.uploadFile(
                "F2",
                "Photo.png",
                25);

        service.downloadFile("F1");

        service.deleteFile("F2");

        service.showStorageStats();
    }
}