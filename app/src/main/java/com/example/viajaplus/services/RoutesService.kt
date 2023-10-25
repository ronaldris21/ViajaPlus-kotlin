package com.example.viajaplus.services
import com.example.viajaplus.models.Route
import java.time.LocalTime

//object es como un singleton. Existe un solo Objeto duran toda la aplicacion
object RoutesService {
    private val routes = mutableListOf<Route>()

    init {

        routes.add(Route(1, "Murcia", "Alicante", LocalTime.of(8, 0), LocalTime.of(10, 30), 25.50))
        routes.add(Route(2, "Alicante", "Murcia", LocalTime.of(16, 0), LocalTime.of(18, 30), 25.50))
        routes.add(Route(3, "Alicante", "Valencia", LocalTime.of(9, 15), LocalTime.of(11, 45), 30.00))
        routes.add(Route(4, "Valencia", "Alicante", LocalTime.of(16, 0), LocalTime.of(18, 30), 30.00))
        routes.add(Route(5, "Murcia", "Granada", LocalTime.of(7, 30), LocalTime.of(11, 0), 40.75))
        routes.add(Route(6, "Granada", "Murcia", LocalTime.of(16, 30), LocalTime.of(20, 0), 40.75))
        routes.add(Route(7, "Valencia", "Murcia", LocalTime.of(21, 30), LocalTime.of(23, 45), 40.75))
        routes.add(Route(8, "Murcia", "Valencia", LocalTime.of(2, 0), LocalTime.of(4, 15), 40.75))
        routes.add(Route(9, "Alicante", "Valencia", LocalTime.of(12, 45), LocalTime.of(15, 15), 30.00))
        routes.add(Route(10, "Valencia", "Alicante", LocalTime.of(18, 0), LocalTime.of(20, 30), 30.00))
        routes.add(Route(11, "Murcia", "Alicante", LocalTime.of(5, 30), LocalTime.of(8, 0), 25.50))
        routes.add(Route(12, "Alicante", "Murcia", LocalTime.of(13, 0), LocalTime.of(15, 30), 25.50))
        routes.add(Route(13, "Alicante", "Valencia", LocalTime.of(10, 30), LocalTime.of(12, 0), 30.00))
        routes.add(Route(14, "Valencia", "Alicante", LocalTime.of(14, 30), LocalTime.of(17, 0), 30.00))
        routes.add(Route(15, "Murcia", "Valencia", LocalTime.of(21, 45), LocalTime.of(0, 15), 30.00))
        routes.add(Route(16, "Valencia", "Murcia", LocalTime.of(2, 30), LocalTime.of(5, 0), 30.00))
        routes.add(Route(17, "Alicante", "Granada", LocalTime.of(9, 0), LocalTime.of(12, 0), 40.75))
        routes.add(Route(18, "Granada", "Alicante", LocalTime.of(14, 30), LocalTime.of(17, 30), 40.75))
        routes.add(Route(19, "Murcia", "Granada", LocalTime.of(5, 45), LocalTime.of(9, 15), 50.25))
        routes.add(Route(20, "Granada", "Murcia", LocalTime.of(14, 0), LocalTime.of(17, 30), 50.25))
        routes.add(Route(21, "Valencia", "Alicante", LocalTime.of(6, 15), LocalTime.of(8, 45), 35.75))
        routes.add(Route(22, "Alicante", "Valencia", LocalTime.of(15, 30), LocalTime.of(18, 0), 35.75))
        routes.add(Route(23, "Murcia", "Valencia", LocalTime.of(9, 30), LocalTime.of(12, 0), 45.50))
        routes.add(Route(24, "Valencia", "Murcia", LocalTime.of(17, 45), LocalTime.of(20, 15), 45.50))
        routes.add(Route(25, "Alicante", "Murcia", LocalTime.of(5, 30), LocalTime.of(8, 0), 20.00))
        routes.add(Route(26, "Murcia", "Alicante", LocalTime.of(14, 0), LocalTime.of(16, 30), 20.00))
        routes.add(Route(27, "Valencia", "Granada", LocalTime.of(9, 0), LocalTime.of(12, 0), 35.75))
        routes.add(Route(28, "Granada", "Valencia", LocalTime.of(14, 30), LocalTime.of(17, 30), 35.75))
        routes.add(Route(29, "Murcia", "Granada", LocalTime.of(6, 30), LocalTime.of(9, 15), 45.50))
        routes.add(Route(30, "Granada", "Murcia", LocalTime.of(15, 0), LocalTime.of(17, 45), 45.50))
        routes.add(Route(31, "Alicante", "Valencia", LocalTime.of(7, 45), LocalTime.of(10, 45), 25.50))
        routes.add(Route(32, "Valencia", "Alicante", LocalTime.of(16, 0), LocalTime.of(18, 30), 25.50))
        routes.add(Route(33, "Valencia", "Murcia", LocalTime.of(12, 0), LocalTime.of(14, 30), 20.00))
        routes.add(Route(34, "Murcia", "Valencia", LocalTime.of(20, 0), LocalTime.of(22, 30), 20.00))
        routes.add(Route(35, "Alicante", "Granada", LocalTime.of(8, 0), LocalTime.of(10, 30), 40.75))
        routes.add(Route(36, "Granada", "Alicante", LocalTime.of(16, 0), LocalTime.of(18, 30), 40.75))
        routes.add(Route(37, "Valencia", "Alicante", LocalTime.of(13, 0), LocalTime.of(15, 30), 30.00))
        routes.add(Route(38, "Alicante", "Valencia", LocalTime.of(19, 15), LocalTime.of(21, 45), 30.00))
        routes.add(Route(39, "Murcia", "Alicante", LocalTime.of(10, 45), LocalTime.of(13, 15), 45.50))
        routes.add(Route(40, "Alicante", "Murcia", LocalTime.of(18, 30), LocalTime.of(21, 0), 45.50))
        routes.add(Route(41, "Alicante", "Murcia", LocalTime.of(0, 15), LocalTime.of(2, 45), 40.75))
        routes.add(Route(42, "Murcia", "Alicante", LocalTime.of(5, 0), LocalTime.of(7, 30), 40.75))
        routes.add(Route(43, "Valencia", "Murcia", LocalTime.of(21, 0), LocalTime.of(23, 30), 30.00))
        routes.add(Route(44, "Murcia", "Valencia", LocalTime.of(2, 45), LocalTime.of(5, 15), 30.00))
        routes.add(Route(45, "Alicante", "Valencia", LocalTime.of(12, 0), LocalTime.of(14, 30), 35.75))
        routes.add(Route(46, "Valencia", "Alicante", LocalTime.of(18, 30), LocalTime.of(21, 0), 35.75))
        routes.add(Route(47, "Murcia", "Alicante", LocalTime.of(3, 30), LocalTime.of(6, 0), 50.25))
        routes.add(Route(48, "Alicante", "Murcia", LocalTime.of(16, 30), LocalTime.of(19, 0), 50.25))
        routes.add(Route(49, "Valencia", "Granada", LocalTime.of(7, 30), LocalTime.of(10, 0), 20.50))
        routes.add(Route(50, "Granada", "Valencia", LocalTime.of(15, 45), LocalTime.of(18, 15), 20.50))

    }

    fun getRoutes(): List<Route> {
        return routes
    }
}
