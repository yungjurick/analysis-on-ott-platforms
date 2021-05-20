# Analysis on OTT Platforms - Netflix
This repository entails the codebase for analysis on the Netflix dataset. For more detail about this project, please refer to the medium article linked below:

https://yungjurick.medium.com/data-analysis-on-ott-platforms-which-service-should-i-choose-8eed953ff7d2

## File Structure

### Client (root)

https://drive.google.com/drive/folders/1zdinFAN-NrXzGsVE8jW4wjyHJ-0ne3QS?usp=sharing

* **Netflix**: Contains Original Data files and Cleaned Data files
* **IMDB_data**: Contains Original IMDB Data Files
* **GroupLens_MovieLens**: Contains Original GroupLens Files
* **Screenshot**: Contains Screenshots of the process

### HDFS
output/*
* Contains Hadoop results for Data Cleaning

count/*
* Contains Hadoop results for Data Profiling

grouplens/*
* Contains original files for GroupLens Data
  
The rest of the files(original, cleaned, processed through Spark) are on the base directory.

### Code
- ana_code: Analysis Code Commands
- data_ingest: Data Ingestion Commands
- etl_code:
  - Hadoop MapReduce: Hadoop MR Commands for Cleaning Base Datasets
  - Spark: Spark Commands for ETL process and dataframe creation
- profiling_code: Hadoop MR Commands for Data Profiling Base Datasets
- screenshot: Screenshots of the steps for the whole process for Netflix Analysis




## Dataset Sources

1. Netflix Movie & TV Shows
https://www.kaggle.com/shivamb/netflix-shows

2. Netflix Original Movies
https://www.kaggle.com/swapnilbhange/netflix-original-movies

3. IMDb Movies Data
https://datasets.imdbws.com/

4. GroupLens Movies Data & Genome Tag Data
https://grouplens.org/datasets/movielens/


## Steps to Reproduce

Download the "root" folder using the above Google Drive link.

### A - [Data Ingestion]
Run the following commands in the commands text file (data_ingest/commands.txt):
- Use the -put commands in the appropriate folder directory within the root folder:
  - For Netflix, Use commands under "Netflix" Directory of Client
  - For IMDb, Use commands under "IMDB_data" Directory of Client
  - For GroupLens, Use commands under "GroupLens_MovieLens" Directory of Client
- With [Data Ingestion] step, all the files should be loaded onto HDFS.


### B - [ETL (Hadoop)]
NetflixClean: Data Clean Code for Netflix Movies & TV Shows Data.
  - Removes unnecessary columns
  - Get Duration in numerical form
  - Filters only movies

NetflixOriginalClean: Data Clean Code for Netflix Original Movies Data.
  - Change released date to year

Execute the following steps:
- Download NetflixClean.jar, NetflixOriginalClean.jar from Hadoop MapReduce directory under etl_code
- Run Commands (Hadoop-MapReduce-DataClean.txt)
- With [ETL (Hadoop)] step, all the cleaned files should be loaded onto HDFS.


### C - [PROFILING (Hadoop)]
Execute the following steps:
- Download CountRecs.jar from profiling_code/
- Run Commands (Hadoop-MapReduce-DataProfiling.txt)


### D - [ETL (Spark) - 1]
This ETL Stage is to create the Netflix Final Dataframe.
- Run the codes (can be found in "2. Spark" in etl_code) in the following order after starting spark-shell:
  1. Netflix Mid-Schema Dataframe.txt (Joins Movies Dataset with Original Movies Dataset)
  2. Netflix Final Dataframe.txt (Joins Mid-Schema Dataframe with IMDb Dataset)
- With [ETL (Spark) - 1] step, "netflix_final_data" is created - the netflix final dataframe for analysis use

### E - [IMDb Analysis (Spark) - 1]
- Average Movie Rating of each converted age groups
- Genre - Count of Age Groups & Average rating
- Run the code (1. IMDb Data Analysis.txt) found in ana_code

### F - [ETL (Spark) - 2]
- This ETL Stage is to create the Netflix-GroupLens Final Dataframe.
- Run the codes (can be found in "2. Spark" in etl_code) in the following order after starting spark-shell:
  1. Netflix Grouplens Average Rating & Genome Tag Score.txt
  2. Netflix Grouplens Final Dataframe.txt
- With [ETL (Spark) - 2] step, "netflix_grouplens_rating", "netflix_grouplens_genome", "netflix_grouplens_final" is created - the netflix-grouplens final dataframe for analysis use

### G - [IMDb Analysis (Spark) - 2]
Average GroupLens Rating of Movies
- Run the code (2. GroupLens Analysis.txt) found in ana_code

Tags with higher than 80% relevance (Original Movies)
- Tag Count, Tag Relevance, and Average of Average GroupLens Rating for the Tag

Tags with higher than 80% relevance (Not Original Movies)
- Tag Count, Tag Relevance, and Average of Average GroupLens Rating for the Tag

## Screenshots

For specific results of the commands, please refer to the following sections:

B ~ C: [1. Netflix Movie & Netflix Original Movie Data Clean and Profile]

D: [2. Netflix Dataframe Preparation]

E: [3. Netflix IMDB Data Analysis]

F: [4. GroupLens Dataframe Preparation]

G: [5. GroupLens Data Analysis]
