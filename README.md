# BTP
B. Tech Project (Dec 2019 - November 2020)

## Title: Android Application For Use in Powerplants

#### Background: 
Most powerplants employ smart-meters, placed around the powerplant, to detect and record various parameters. These then upload these recorded values to a master computer (all are on a common network, say WiFi), located in the control-room of the powerplant. When the value of these is needed (by the manager), he needs to be physically present in the control-room. The required parameters are then displayed to him through a web-app which he runs in his browser. 

Our job was to develop an app that would allow the manager to access these parameters anywhere within the powerplant through his phone. 

#### Features:
Through the app, the user is able to achieve the same functionality as the web app, with the added benefit of portability and convenience. 

#### Functionality: 
Since we didn't get to work on the actual powerplant (due to COVID-19), we've replicated the system as follows: 

1. The data (or the parameters) is hosted on an AWS server (comparable to the master computer). A URL is then entered to access the said data. 
2. The data is randomly generated: we've studied the ranges and trends in actual data and developed algorithms that would mimic the real-world data as much as possible. 
3. The app fetches the data from the server and displays the results. 

#### Added Featurs: 
Beyond the required work, we've also added a machine-learning implementation within the app: the model is trained beforehand and its hyperparameters are stored on the server. The app then fetches these to create a plot between the actual value of power generation and the predicted value of power generation, effectively trying to minimize the difference between the two. 


