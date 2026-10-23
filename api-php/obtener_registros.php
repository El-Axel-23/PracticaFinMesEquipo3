<?php
header("Content-Type: application/json; charset=UTF-8");
require_once "config/conexion.php";

try{
    $sql = "SELECT c.id, c.carrera_id, c.nombre, c.url_imagen, c.cupo_maximo, COUNT(i.id) AS inscritos
    FROM cursos c LEFT JOIN inscripciones i ON i.curso_id = c.id";

    if($carrera_id > 0) {
        $stmt = $pdo->prepare($sql." WHERE c.carrera_id = ? GROUP BY c.id ORDER BY c.nombre ASC");
        $stmt->execute([$carrera_id]);
    }
    else{
        $stmt = $pdo->query($sql. " GROUP BY c.id ORDER BY c.nombre ASC");
    }
    $cursos = $stmt->fetchAll();

    //casteo
    $total = 0;
    foreach($cursos as &$c){
        $c['id'] = (int)$c['id'];
        $c['carrera_id'] = (int)$c['carrera_id'];
        $c['cupo_maximo'] = (int)$c['cupo_maximo'];
        $c['inscritos'] = (int)$c['inscritos'];
        $total += $c['inscritos'];
    }

    echo json_encode([
        "total"=>$total,
        "cursos"=>$cursos
    ]);
}catch(Exception $e){
    header("HTTP/1.1 500 Internal Server Error");
    echo json_encode(["error"=>"Error al obtener registros: ".$e->getMessage()]);
}
?>