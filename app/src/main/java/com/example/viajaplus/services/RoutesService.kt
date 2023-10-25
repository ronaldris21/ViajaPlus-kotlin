package com.example.viajaplus.services
import com.example.viajaplus.models.Route
import java.time.LocalTime

//object es como un singleton. Existe un solo Objeto duran toda la aplicacion
object RoutesService {
    private val routes = mutableListOf<Route>()

    init {
        // Add 25 routes to the singleton object
        routes.add(Route(1, "Murcia", "Alicante", LocalTime.of(8, 0), LocalTime.of(10, 30), 25.50))
        routes.add(Route(2, "Alicante", "Valencia", LocalTime.of(9, 15), LocalTime.of(11, 45), 30.00))
        routes.add(Route(3, "Murcia", "Granada", LocalTime.of(7, 30), LocalTime.of(11, 0), 40.75))
        routes.add(Route(4, "Valencia", "Alicante", LocalTime.of(10, 30), LocalTime.of(12, 45), 25.50))
        routes.add(Route(5, "Granada", "Valencia", LocalTime.of(8, 45), LocalTime.of(14, 30), 50.25))
        routes.add(Route(6, "Almeria", "Cartagena", LocalTime.of(9, 0), LocalTime.of(11, 30), 20.00))
        routes.add(Route(7, "Alicante", "Murcia", LocalTime.of(12, 0), LocalTime.of(14, 15), 25.50))
        routes.add(Route(8, "Cartagena", "Valencia", LocalTime.of(11, 30), LocalTime.of(14, 45), 35.75))
        routes.add(Route(9, "Murcia", "Almeria", LocalTime.of(14, 15), LocalTime.of(16, 30), 30.00))
        routes.add(Route(10, "Valencia", "Granada", LocalTime.of(12, 45), LocalTime.of(16, 0), 45.50))
        routes.add(Route(11, "Alicante", "Cartagena", LocalTime.of(15, 0), LocalTime.of(17, 15), 20.50))
        routes.add(Route(12, "Cartagena", "Murcia", LocalTime.of(17, 0), LocalTime.of(19, 15), 15.50))
        routes.add(Route(13, "Almeria", "Granada", LocalTime.of(15, 30), LocalTime.of(17, 45), 22.00))
        routes.add(Route(14, "Granada", "Murcia", LocalTime.of(18, 30), LocalTime.of(20, 45), 28.50))
        routes.add(Route(15, "Valencia", "Murcia", LocalTime.of(19, 15), LocalTime.of(21, 30), 20.50))
        routes.add(Route(16, "Cartagena", "Alicante", LocalTime.of(20, 0), LocalTime.of(22, 15), 25.50))
        routes.add(Route(17, "Murcia", "Cartagena", LocalTime.of(21, 30), LocalTime.of(23, 45), 15.50))
        routes.add(Route(18, "Almeria", "Alicante", LocalTime.of(21, 45), LocalTime.of(0, 0), 30.00))
        routes.add(Route(19, "Granada", "Valencia", LocalTime.of(23, 0), LocalTime.of(1, 15), 35.75))
        routes.add(Route(20, "Valencia", "Murcia", LocalTime.of(23, 30), LocalTime.of(1, 45), 20.50))
        routes.add(Route(21, "Cartagena", "Granada", LocalTime.of(0, 15), LocalTime.of(2, 30), 40.75))
        routes.add(Route(22, "Murcia", "Almeria", LocalTime.of(2, 0), LocalTime.of(4, 15), 25.50))
        routes.add(Route(23, "Alicante", "Granada", LocalTime.of(1, 30), LocalTime.of(3, 45), 35.75))
        routes.add(Route(24, "Granada", "Cartagena", LocalTime.of(3, 0), LocalTime.of(5, 15), 20.50))
        routes.add(Route(25, "Almeria", "Valencia", LocalTime.of(4, 15), LocalTime.of(6, 30), 30.00))
        routes.add(Route(26, "Alicante", "Murcia", LocalTime.of(8, 0), LocalTime.of(10, 30), 25.50))
        routes.add(Route(27, "Murcia", "Alicante", LocalTime.of(9, 15), LocalTime.of(11, 45), 30.00))
        routes.add(Route(28, "Granada", "Valencia", LocalTime.of(7, 30), LocalTime.of(11, 0), 40.75))
        routes.add(Route(29, "Valencia", "Alicante", LocalTime.of(10, 30), LocalTime.of(12, 45), 25.50))
        routes.add(Route(30, "Murcia", "Valencia", LocalTime.of(8, 45), LocalTime.of(14, 30), 50.25))
        routes.add(Route(31, "Alicante", "Granada", LocalTime.of(9, 0), LocalTime.of(11, 30), 20.00))
        routes.add(Route(32, "Valencia", "Cartagena", LocalTime.of(12, 0), LocalTime.of(14, 15), 25.50))
        routes.add(Route(33, "Murcia", "Almeria", LocalTime.of(11, 30), LocalTime.of(14, 45), 35.75))
        routes.add(Route(34, "Almeria", "Granada", LocalTime.of(14, 15), LocalTime.of(16, 30), 30.00))
        routes.add(Route(35, "Cartagena", "Alicante", LocalTime.of(12, 45), LocalTime.of(16, 0), 45.50))
        routes.add(Route(36, "Alicante", "Valencia", LocalTime.of(15, 0), LocalTime.of(17, 15), 20.50))
        routes.add(Route(37, "Valencia", "Murcia", LocalTime.of(17, 0), LocalTime.of(19, 15), 15.50))
        routes.add(Route(38, "Cartagena", "Valencia", LocalTime.of(15, 30), LocalTime.of(17, 45), 22.00))
        routes.add(Route(39, "Murcia", "Granada", LocalTime.of(18, 30), LocalTime.of(20, 45), 28.50))
        routes.add(Route(40, "Granada", "Murcia", LocalTime.of(19, 15), LocalTime.of(21, 30), 20.50))
        routes.add(Route(41, "Valencia", "Alicante", LocalTime.of(20, 0), LocalTime.of(22, 15), 25.50))
        routes.add(Route(42, "Murcia", "Cartagena", LocalTime.of(21, 30), LocalTime.of(23, 45), 15.50))
        routes.add(Route(43, "Almeria", "Alicante", LocalTime.of(21, 45), LocalTime.of(0, 0), 30.00))
        routes.add(Route(44, "Granada", "Valencia", LocalTime.of(23, 0), LocalTime.of(1, 15), 35.75))
        routes.add(Route(45, "Valencia", "Murcia", LocalTime.of(23, 30), LocalTime.of(1, 45), 20.50))
        routes.add(Route(46, "Cartagena", "Granada", LocalTime.of(0, 15), LocalTime.of(2, 30), 40.75))
        routes.add(Route(47, "Murcia", "Almeria", LocalTime.of(2, 0), LocalTime.of(4, 15), 25.50))
        routes.add(Route(48, "Alicante", "Granada", LocalTime.of(1, 30), LocalTime.of(3, 45), 35.75))
        routes.add(Route(49, "Granada", "Cartagena", LocalTime.of(3, 0), LocalTime.of(5, 15), 20.50))
        routes.add(Route(50, "Almeria", "Valencia", LocalTime.of(4, 15), LocalTime.of(6, 30), 30.00))

    }

    fun getRoutes(): List<Route> {
        return routes
    }
}
