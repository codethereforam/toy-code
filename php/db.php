<table border='1' align='center'>
    <tr>
        <th>id</th>
        <th>username</th>
        <th>password</th>
    </tr>
    <?php

    class TableRows extends RecursiveIteratorIterator
    {
        function current()
        {
            return "<td>" . parent::current() . "</td>";
        }

        function beginChildren()
        {
            echo "<tr>";
        }

        function endChildren()
        {
            echo "</tr>" . "\n";
        }
    }

    $serverName = "localhost";
    $username = "root";
    $password = "978299";

    try {
        $connection = new PDO("mysql:host=$serverName;dbname=register", $username, $password);
        $connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        // create
       /* $sql = 'insert into user(username, password) values(:username, :password)';
        $stmt = $connection->prepare($sql);
        $stmt->bindParam(':username', $username);
        $stmt->bindParam(':password', $password);
        $username = 'u7';
        $password = 'p7';
        $stmt->execute();
        echo $stmt->rowCount() . ' record created';*/

        // update
       /* $sql = 'update user set password=:password where username=:username';
        $stmt = $connection->prepare($sql);
        $stmt->bindParam(':username', $username);
        $stmt->bindParam(':password', $password);
        $username = 'u7';
        $password = 'pwd7';
        $stmt->execute();
        echo $stmt->rowCount() . ' record updated';*/

        // delete
        /*$sql = 'delete from user where username=:username';
        $stmt = $connection->prepare($sql);
        $stmt->bindParam(':username', $username);
        $username = 'u7';
        $stmt->execute();
        echo $stmt->rowCount() . ' record deleted';*/

        // retrieve
        $stmt = $connection->prepare("SELECT * FROM user where id >:id");
        $stmt->bindParam(':id', $id);
        $id = 7;
        $stmt->execute();
        $stmt->setFetchMode(PDO::FETCH_ASSOC);
        $result = $stmt->fetchAll();
        // traverse result
        /* for ($i = 0; $i < count($result); $i++) {
             foreach ($result[$i] as $key => $value) {
                 echo $key . " | " . $value;
                 echo "<br>";
             }
             echo '-----------<br/>';
         }
         foreach (new RecursiveIteratorIterator(new RecursiveArrayIterator($result)) as $key => $value) {
             echo $key . ' -- ' . $value . '<br />';
         }*/
        foreach (new TableRows(new RecursiveArrayIterator($result)) as $k => $v) {
            echo $v;
        }
    } catch (PDOException $error) {
        echo "Connection failed: " . $error->getMessage();
    }
    $connection = null;
    ?>
</table>