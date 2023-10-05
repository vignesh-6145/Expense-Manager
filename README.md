<h1 align="center" id="title">Expense Manger Backend</h1>

<p align="center"><img src="https://socialify.git.ci/vignesh-6145/Expense-Manager/image?description=1&amp;descriptionEditable=A%20simple%20way%20to%20manage%20your%20expenses&amp;issues=1&amp;language=1&amp;name=1&amp;pattern=Solid&amp;pulls=1&amp;theme=Light" alt="project-image"></p>


<p id="description">Backend Component for the Expense Manager</p>

<p align="center"><img  src="https://img.shields.io/badge/Starter_project-purple" alt="shields"> <img src="https://img.shields.io/badge/Philosphy-Learn_by_doing-blue" alt="shields"> <img src="https://img.shields.io/badge/Learning%20project-green" alt="shields"></p>


  ### Background
  This was project was part of learning journey of JAVA. I started developing this project as a learning project (learning by building). This may lack many best practises but I tried my level best
  to understand every concept I learned without directly copy and pasting. Please feel free to provide your feedback.
  
  Thanks and Regards
  
  Vignesh Bandla
  
<h2>🧐 Features</h2>

Here're some of the project's best features:

*   Secure Authentication and Authorization
*   OpenAPI Integration
*   Split the Bill with your friends(planned)
*   Summary Dashoard(on it)
<h2>🛠️ Installation Steps:</h2>
As the Docker support was added to the project one can simple construct the image and run the container.Will be further by pushing the image to the docker registry

### Steps to build the image 

- run the command ./mvnw install
- build the image
 ex :- `docker build -t exp-mngr-app .`
  - name of the image **exp-mngr-app** 
- run the container by mapping the container port 8081 (since specified in applicatoin.properties) to any local port
 ex :- `docker run -p 8082:8081 exp-mngr-app`
  - local port : 8082
  - container port : 8081
 - now you can use the API's with tools such as postman/talend platforms
<h2>Endpoints Catered</h2>

| S.No | Endpoint                             | Method |
|------|--------------------------------------|--------|
| 1    | /users                               | GET    |
| 2    | /users/{userId}                      | GET    |
| 3    | /users/{userId}/addExpense           | POST   |
| 4    | /users/{userId}/expenses             | GET    |
| 5    | /users/{userId}/expenses/{expenseId} | GET    |
| 6    | /users/addUser                       | POST   |
| 7    | /users/{userId}/updateExpense        | PUT    |
| 8    | /updateUser                          | PUT    |

  
  
<h2>💻 Built with</h2>

Technologies used in the project:

*   Java
*   Spring boot
*   Spring Data
*   Spring security
*   Docker
*   Eclipse

<h2>Feedback & Sugestions</h2>

Thanks for taking time and looking at this project.  
I know this was not perfect and falls behind in many areas  
Please let me know where can I improve, would be grateful to you
<p align="left">
Email : <a href = "mailto: abc@example.com">vignesh.bandla01@gmail.com</a>
LinkedIn : <a href="https://www.linkedin.com/in/bandla-vignesh-b3b655108/"> Vignesh Bandla</a>
Instagram : <a href="https://www.instagram.com/vignesh_bandla/"> Vignesh Bandla</a>

</p>
