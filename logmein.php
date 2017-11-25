<?php
// Establish a connection to the database (you need to set the connection parameters in the string).
// If you want to use a different database, you need to change the following settings to the appropriate values.
  $dbconn = pg_connect("host=soit-db-pro-2.ucc.usyd.edu.au port=5432 dbname=y17s2i1003_skal7504 user=y17s2i1003_skal7504 password=470528613");

// Get parameters from the query string.
// Creates a variable for each query parameter.
$str = $_SERVER['QUERY_STRING'];
parse_str($str);

// Print the vale of the parameters (currently commented out - delete the '#' characters to include these lines).
#echo "$username\n";
#echo "$passd\n";

// Construct and execute the sql query.
$result = pg_query_params($dbconn, 'SELECT * FROM users WHERE username = $1 and passwd = $2', array("$username", "$passwd"));

// Check the result of the query and write appropriate output.
if (pg_fetch_row($result)) {
  echo "success\n";

}
else
{
  echo "invalid\n";
}
?>
