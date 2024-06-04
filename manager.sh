#!/bin/bash

# Function to install Docker using Snap
install_docker() {
    echo "Docker is not installed. Installing Docker..."
    sudo curl -fsSL https://get.docker.com/ | sh
    echo "Docker has been installed."
}

# Check if Docker is installed
if ! command -v docker &> /dev/null; then
    install_docker
fi

# Prompt the user for input
echo "Please enter an option (build, start, stop, clean all, rebuild, log or exit):"
read option

# Execute corresponding commands based on the user's input
case $option in
    build)
        echo "Building the Docker image..."
        docker-compose build
        ;;
    start)
        echo "Starting the Docker Compose application..."
        docker-compose up -d
        ;;
    stop)
        echo "Stopping the Docker Compose application..."
        docker-compose down
        ;;
    clean)
        echo "Cleaning all Docker resources..."
        docker system prune -a
        ;;
    rebuild)
        echo "Rebuilding the Docker image and restarting the container..."
        docker-compose build
        docker-compose down
        docker-compose up -d
        ;;
    log)
        echo "Displaying the logs of the Docker Compose application..."
        docker-compose logs -f
        ;;
    exit)
        echo "Exiting the script..."
        exit   0
        ;;
    *)
        echo "Invalid option. Please enter build, start, stop, clean all, rebuild, log or exit."
        ;;
esac