#!/bin/bash

# Function to increment the version number
increment_version() {
  local version=$1
  local major minor patch
  IFS='.' read -r major minor patch <<< "$version"
  patch=$((patch + 1))
  echo "$major.$minor.$patch"
}

# Read the current version from a file or set it manually
version_file="version.txt"
if [[ -f $version_file ]]; then
  current_version=$(cat $version_file)
else
  current_version="0.0.0"  # Default version if no file is found
fi

# Increment the version
new_version=$(increment_version $current_version)
echo "Updating version from $current_version to $new_version"

# Save the new version to the file
echo $new_version > $version_file

# Build the new Docker image with the new tag
docker build -t pos-system:$new_version .

# Update the docker-compose.yml file with the new tag
sed -i "s/pos-system:.*$/pos-system:$new_version/" docker-compose.yml

# Bring up the services with Docker Compose
docker-compose down
docker-compose up -d --build

echo "Deployment complete with tag pos-system:$new_version"