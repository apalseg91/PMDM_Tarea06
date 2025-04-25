package dam.pmdm.googlemapsapp;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.Manifest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//         ==============================
//           Inicialización del mapa
//         ==============================
//         Obtiene el fragmento del mapa desde el layout usando su ID.
//         Luego registra esta actividad como callback para recibir
//         el mapa de forma asíncrona cuando esté listo (onMapReady).
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // Inicializa el cliente de ubicación
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }

//     ==============================
//      Metodo llamado cuando el mapa está listo para usarse
//     ==============================
//     - Se obtiene la instancia del mapa y se guarda en la variable mMap.
//     - Se comprueba si la app tiene permiso de ubicación.
//     - Se añade un marcador personalizado en la ubicación especificada.
//     - Se mueve la cámara del mapa hacia esa ubicación con un nivel de zoom adecuado.
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        LatLng iesAguadulce = new LatLng(36.80934308883409, -2.5832089509371428);

//        Añadir marcador
        mMap.addMarker(new MarkerOptions()
                .position(iesAguadulce)
                .title("IES Aguadulce")
                .snippet("FPaD")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.crash)));


//        Petición de permisos de ubicación al usuario mediante dialogo
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }

        showMyLocationOnMap();
    }

//    Muestra la localización del usuario en el mapa y mueve la cáma a esa ubicación
    private void showMyLocationOnMap() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, location -> {
                        if (location != null) {
                            LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
//                             ==============================
//                             Google Maps Zoom Levels
//                             ==============================
//                             Zoom 1   → Mundo (muy alejado)
//                             Zoom 5   → Continentes/regiones grandes
//                             Zoom 10  → Ciudad o zona amplia
//                             Zoom 15  → Calles y barrios (ideal para ubicaciones locales)
//                             Zoom 18  → Detalle de calle (coches, edificios)
//                             Zoom 21  → Máximo zoom (muy cerca, vista de edificios o interiores)
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 10));
                        }
                    });
        }
    }

//     ==============================
//       Respuesta a la petición de permisos
//     ==============================
//     Esta función se ejecuta automáticamente cuando el usuario responde
//     al diálogo de permisos.
//
//     - Comprueba si el permiso fue concedido.
//     - Si lo fue, vuelve a verificar que se tiene el permiso de ubicación.
//     - Si va bien, activa la capa de "mi ubicación" (punto azul) en el mapa.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    showMyLocationOnMap();
                }
            }
        }
    }
}