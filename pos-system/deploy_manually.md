
### `update_and_deploy.sh`

```bash
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
```

### Steps to Use the Script

1. **Save the Script**: Save the above script to a file named `update_and_deploy.sh` in your project directory (`~/pharmacy-pos-main-backend/pos-system`).

2. **Make the Script Executable**: Make the script executable by running the following command:

    ```bash
    chmod +x update_and_deploy.sh
    ```

3. **Create a `version.txt` File**: Create a file named `version.txt` in the same directory, and add the initial version number (e.g., `0.0.0`):

    ```bash
    echo "0.0.0" > version.txt
    ```

4. **Run the Script**: Execute the script whenever you make changes to your code and need to update the Docker tag, rebuild the Docker image, and bring up the services:

    ```bash
    ./update_and_deploy.sh
    ```
