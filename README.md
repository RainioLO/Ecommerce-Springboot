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
    - add the logout function in fragments
    - topbar index.html line 144 
    - sidebar index.html line 13

### 2.1 Category
    - Model (Category)
    - repo(categoryRepo)
    - controller (GetMapping: /categories)
    - model.addAttribute("title", "Category"); bind with the class Category
    
    - model.addAttribute("categories", categories);
      model.addAttribute("size", categories.size());
      model.addAttribute("title", "Category");

    - add constraints if categories size = 0
      <div th:if = "${size == 0}"></div> 
      categories.html line 26/30

    - modal bootstrap categories.html line 29/63
    - line 65 add th:action with the action "add-category" in PostMapping

    - @PostMapping("/add-category") redirect:/categories --> @ModelAttribute("categoryNew") Category category
    - <form th:action="@{/add-category}" method="post" th:object="${categoryNew}"> add the route
    - <input type="text" class="form-control" th:field = "*{name}" id="recipient-name"> add the attribute
    - --> line 65,74.79 submit -> action -> method -> object
        <button type="submit" class="btn btn-primary">Save</button>

#### 2.1.1 Category Layout
    - index.html line 51 <a class="collapse-item" href="buttons.html" th:href= "@{/categories}">Manage Category</a>
        --> replace buttons.html with categories.html --> new layout
        --> with controller --> access index 
        --> access categories, by GetMapping = (value = "/categories"), return categories.html 
    - replace with the fragements <head th:replace="fragments ::header"> </head>
    - added table bootstrap Striped rows (categories.html line 341 - 358)
    - index.html line 41 <!-- Nav Item - Pages Collapse Menu -->

    - show from the database <tr th:each="category : ${categories}">, line 39
    - <th scope="row" th:text="${category.id}"></th> -refering to Category Class
    - link PostMapping to line 30, do the action --> 
            <div th:if = "${success}" class="text-center alert alert-success"></div> -> Notification
            <p th:text="${success}"></p> -> text in success in CateController

### 2.2 Product
    - Model (Product)

## 3. Add Principal for Security
        if (principal == null){
            return "redirect:/login";
        }

---

# <b> 30/04/2024 </b>
## 1. Categories Edit Function (CRUD)
    - add update button line 49
      <a id="editButton" th:href="@{/getById/(id=${category.id})}class="btn btn-primary">Update</a>
    
    - show the data in the mainlayout 
        <tbody>
            <tr th:each="category : ${categories}">
                <th scope="row" th:text="${category.id}"></th>
                <td th:text="${category.name}"></td>
                <td></td>
            </tr>
        </tbody>
    
    - add Update button, line 50
        <a id="editButton" th:href="@{/getById/(id = ${category.id})}" class="btn btn-primary">Update</a>
    - find the object by id -> JSON response body
    - 




