# Ecommerce-Springboot
___
## 23/04/2024
### 1. Log-out function
    Location: LoginController line 60
    .logoutSuccessUrl("/login?logout") --> locate at index.html
    --> redirect to Login.html with <a class="btn btn-primary" 
        th:href = "@{/login?logout}" href="login.html">Logout</a>
    <div th:if = "${param.logout}" class = "alert alert-success text-center">
        You have been logged out!
    </div> # login.html line 47

### 2. fragments handling
    <head th:fragment="header"> -- fragments.html
    <head th:replace="fragments :: header"> -- used in forgot-password.html, etc 
    </head>






<br> <!-- Empty line or line break -->