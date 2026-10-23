
<?php

header("Content-Type: application/json; charset=UTF-8");

require_once "config/conexion.php";

$carrera_id = isset($_GET['carrera_id'])? intval($_GET['carrera_id']): 0;

try {

    $sql = "SELECT 
    c.id,c.carrera_id,ca.carrera,c.codigo,c.nombre, c.descripcion,c.url_imagen,c.salon,c.edificio,c.total_horas,c.dias_atencion,c.horario,c.cupo_maximo,
     COUNT(i.id) AS inscritos FROM cursos AS c INNER JOIN carreras AS ca ON c.carrera_id = ca.id LEFT JOIN inscripciones AS i ON c.id = i.curso_id";

    if ($carrera_id > 0) {
        $sql .= " WHERE c.carrera_id = :carrera_id";
    }

    $sql .= " GROUP BY c.id, c.carrera_id, ca.carrera, c.codigo, c.nombre, c.descripcion, c.url_imagen, c.salon, c.edificio, c.total_horas, c.dias_atencion, c.horario, c.cupo_maximo ORDER BY c.id ASC";

    $stmt = $conexion->prepare($sql);

    if ($carrera_id > 0) {
        $stmt->bindValue( ':carrera_id', $carrera_id, PDO::PARAM_INT );
    }

    $stmt->execute();

    $cursos = $stmt->fetchAll(PDO::FETCH_ASSOC);

    echo json_encode(
        [ "success" => true,
        "total" => count($cursos),
        "cursos" => $cursos ],
        JSON_UNESCAPED_UNICODE
    );

} catch (PDOException $e) {
    http_response_code(500);
    echo json_encode(
        ["success" => false,
        "mensaje" => "Error al obtener los registros de cursos" ],
        JSON_UNESCAPED_UNICODE
    );
}
?>
