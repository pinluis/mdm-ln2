# Super Resolution Image Enhancer

This is a web application that utilizes a deep learning model to enhance the resolution of images. It uses a pre-trained model from DJL (Deep Java Library) and a simple frontend to allow users to upload and enhance their images.

## Features

- Upload a .png image file to enhance its resolution.
- Preview the original and enhanced image side-by-side on the web page.
- Dark themed, user-friendly interface.

## How to Run the Application

This application is containerized using Docker, so you can easily run it on any platform that supports Docker.

1. Clone the repository:

```bash
git clone https://github.com/pinluis/mdm-ln2.git
```

2. Navigate to the project directory:

```bash
cd pinhelui-ln2
```

3. Build the Docker image:

```bash
docker build -t pinhelui-ln2 .
```

4. Run the Docker image:

```bash
docker run -p 8080:8080 pinhelui-ln2
```

5. Open your browser and go to http://localhost:8080

## How to Use the Application

1. Click on the "Choose File" button and select a .png image file to upload.
2. Click on the "Upload" button to upload the image.
3. Wait for the image to be enhanced. This may take a while depending on the size of the image.
4. Once the image has been enhanced, you will see the original and enhanced images side-by-side on the web page.

## How to Stop the Application

1. Press Ctrl+C to stop the application.
2. Run the following command to remove the Docker container:

```bash
docker rm $(docker ps -a -q)
```

3. Run the following command to remove the Docker image:

```bash
docker rmi $(docker images -q)
```
