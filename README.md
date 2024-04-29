# Ecommerce by Springboot
___
# <b> 23/04/2024 </b>
## 1. Log-out function
    - Location: LoginController line 60
    .logoutSuccessUrl("/login?logout") --> locate at index.html
    --> redirect to Login.html with <a class="btn btn-primary" 
        th:href = "@{/login?logout}" href="login.html">Logout</a>
    <div th:if = "${param.logout}" class = "alert alert-success text-center">
        You have been logged out!
    </div> # login.html line 47

## 2. fragments handling
    -<head th:fragment="header"> -- fragments.html
    -<head th:replace="fragments :: header"> 
        -> used in forgot-password.html, etc 
    </head>
    -replace the header and script with fragment.html
<br> <!-- Empty line or line break -->

###### Github
git commit --amend --author="New Committer Name <newcommitter@example.com>" / git push --force

---

# <b> 29/04/2024 </b>
## 1. html configuration with leaf
    -index, login, register, forgot-password, 
        replace with <div th:replace = "script"></div>, 
        <head th:replace = "fragments::header">

    -index.html line 732-737 -> leaf syntax
        <script th:src="@{/vendor/chart.js/Chart.min.js}"></script>

    -<div th:replace = "fragments::script" ></div>

## 2. Create Product and Category
    - Many to one for Product to Category
    - CategoryRepository, ProductRepo for data entry
    - service layer -> call repo to help data treatment

### 2.1 Category
    - Model (Category)
    - repo(categoryRepo)
    - controller (GetMapping: /categories)
    - model.addAttribute("title", "Category"); bind with the class Category

#### 2.1.1 Category Layout
    - index.html line 51 <a class="collapse-item" href="buttons.html" th:href= "@{/categories}">Manage Category</a>
        --> replace buttons.html with categories.html --> new layout
        --> with controller --> access index 
        --> access categories, by GetMapping = (value = "/categories"), return categories.html 
    - replace with the fragements <head th:replace="fragments ::header"> </head>
    - added table bootstrap Striped rows (categories.html line 341 - 358)
    - index.html line 41 <!-- Nav Item - Pages Collapse Menu -->

### 2.2 Product
    - Model (Product)





