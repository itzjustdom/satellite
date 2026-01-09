# Test all input files for the Satellite project
Write-Host "=== Satellite Project - Testing All Input Files ===" -ForegroundColor Green

# Compile the project first
Write-Host "Compiling..." -ForegroundColor Yellow
javac -d target\classes src\main\java\satellite\Satellite.java

# Array of input files to test
$inputFiles = @("input.txt", "input1.txt", "input2.txt", "input3.txt", "input4.txt")

foreach ($file in $inputFiles) {
    Write-Host "`n--- Testing $file ---" -ForegroundColor Cyan
    
    # Copy the input file to current directory
    if (Test-Path "src\main\resources\$file") {
        Copy-Item "src\main\resources\$file" . -Force
        
        # Run the test
        Write-Host "Running test..." -ForegroundColor Gray
        java -cp target\classes satellite.Satellite $file
    } else {
        Write-Host "File $file not found!" -ForegroundColor Red
    }
}

Write-Host "`n=== Testing Complete ===" -ForegroundColor Green