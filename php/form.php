<?php
$name = $age = "";
if($_SERVER['REQUEST_METHOD'] == 'POST') {
    $name = deal_input($_POST['name']);
    $age = deal_input($_POST['age']);
}
function deal_input($data) {
    $data = trim($data);
    $data = stripcslashes($data);
    $data = htmlspecialchars($data);
    return $data;
}
?>

<?php
$welcome = 'welcome';
echo $welcome;
?><br>
<form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post">
    <p>Your name: <input name="name"/></p>
    <p>Your age: <input name="age"/></p>
    <p><input type="submit"/></p>
</form>

<?php
echo 'name: ' . $name;
?><br>
age:
<?php
echo (int)$age;
?><br>

<!--test class-->
<?php
class Car {
    function Car()
    {
        $this->name = "car1";
    }
}

// create an object
$c = new Car();

// show object properties
echo $c->name;
?>


