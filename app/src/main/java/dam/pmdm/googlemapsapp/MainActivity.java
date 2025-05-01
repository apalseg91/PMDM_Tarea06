package dam.pmdm.googlemapsapp;

import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.Manifest;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

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

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationClient;
    //mapa con los markers y sus contraseñas
    private final Map<String, String> contraseñas = new HashMap<String, String>() {{
        put("Isla Wumpa", "HLV");
        put("Gran templo del Cortex", "SVQ");
        put("Coliseo", "GND");
        put("Playa N.Sanity", "ALM");
        put("Templo de los falsos ídolos", "CDB");
        put("Tierras de los N.Sanity", "MLG");
        put("Camino del abismo", "HLV2");
        put("La ciudad perdida", "SVQ2");
        put("Sala del Generador", "XRZ");
        put("Carrera de Rocas", "GND2");
    }};
    private Switch switchUbicacion;
    MediaPlayer woah, crash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switchUbicacion = findViewById(R.id.switch_ubicacion);
        woah = MediaPlayer.create(this, R.raw.woah);
        crash = MediaPlayer.create(this, R.raw.crash);
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
        mMap = googleMap;//cargo el mapa
        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); //textura del mapa
        LatLng donana = new LatLng(37.0446692671068, -6.434814920751725);
        LatLng catedralSVQ = new LatLng(37.38611549909602, -5.993203147477926);
        LatLng palacioCarlosV = new LatLng(37.176983392755886, -3.5899428748528086);
        LatLng mezquitaCDB = new LatLng(37.8793817054009, -4.779156675656026);
        LatLng caboGata = new LatLng(36.774208425608855, -2.24022783781254);
        LatLng malagueta = new LatLng(36.71775634513302, -4.410209411119213);
        LatLng muelleHLV = new LatLng(37.25168203756676, -6.957797476183797);
        LatLng parqeMLuisa = new LatLng(37.37525265463863, -5.98820038981505);
        LatLng alamedaXRZ = new LatLng(36.68096008689473, -6.141117575143555);
        LatLng sierraNevada = new LatLng(37.057093981389045, -3.3121365360346586);

//        Añadir marcador
        mMap.addMarker(new MarkerOptions()
                .position(donana)
                .title("Isla Wumpa")
                .snippet("\nEl idílico paraje en el que comenzamos siempre nuestras aventuras." +
                        "\nActividad: Debes mostrar al monitor tres fotos de tres aves distintas de la Isla Wumpa." +
                        "\nPASS:HLV")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.crash)));
        mMap.addMarker(new MarkerOptions()
                .position(catedralSVQ)
                .title("Gran templo del Cortex")
                .snippet("\nNos adentramos en la guaridad del malvado Dr.Neo." +
                        "\nEntrega al monitor dos de los cristales ocultos en las inmediaciones del Gran Templo.\nPASS:SVQ")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.neo)));
        mMap.addMarker(new MarkerOptions()
                .position(palacioCarlosV)
                .title("Coliseo")
                .snippet("\nToca luchar contra Tiny Tiger." +
                        "\nActividad:Vence al monitor en al mejor de 3 tirando de la soga." +
                        "\nPASS:GND")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.tawna)));
        mMap.addMarker(new MarkerOptions()
                .position(caboGata)
                .title("Playa N.Sanity")
                .snippet("\nLa famosa playa de la Saga, donde entrenar y descansar." +
                        "\nActividad:Muestra al monitor una foto de la cara de Crash Bandicoot." +
                        "\nPASS:ALM")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fosil)));
        mMap.addMarker(new MarkerOptions()
                .position(mezquitaCDB)
                .snippet("\nToca explorar el templo de los falsos ídolos y sacar ventaja antes de enfretarnos al jefe Papu-Papu." +
                        "\nActividad:Di al monitor la suma total de los arcos del templo del Templo." +
                        "\nPASS:CDB")
                .title("Templo de los falsos ídolos")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.aku)));
        mMap.addMarker(new MarkerOptions()
                .position(malagueta)
                .title("Tierras de los N.Sanity")
                .snippet("\nEs hora de relacionarse con los simpáticos habitantes de la zona." +
                        "\nActividad:Muestra al monitor un video corto con 3 mensajes de ánimo de los locales de las Tierras N.Sanity." +
                        "\nPASS:MLG")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.relic)));
        mMap.addMarker(new MarkerOptions()
                .position(muelleHLV)
                .title("Camino del abismo")
                .snippet("\nOtro guiño al famoso nivel del primer videojuego 'Road to Nowhere' en un pueste en ruinas." +
                        "\nActividad:Supera la carrera de obstaculos en el muelle." +
                        "\nPASS:HLV2")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.time)));
        mMap.addMarker(new MarkerOptions()
                .position(parqeMLuisa)
                .title("La ciudad perdida")
                .snippet("\nVamos a explorar la jungla perdida y sus distintas localizaciones." +
                        "\nActividad:Entrega al monitor los 3 tarjetas con cristales que se encuentran perdidas por el parque." +
                        "\nPASS:SVQ2")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.cristal)));
        mMap.addMarker(new MarkerOptions()
                .position(alamedaXRZ)
                .title("Sala del Generador")
                .snippet("\nHa llegado el momento de sabotear los generadores del Dr. Neo.\nActividad:Derriba con las pelotas de espuma al menos 3 generadores.\nPASS:XRZ")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.tropys_mug)));
        mMap.addMarker(new MarkerOptions()
                .position(sierraNevada)
                .title("Carrera de Rocas")
                .snippet("\nVamos a simular el famoso nivel 'Bolder Dash' del primer videojuego, donde uan roca gigante nos perseguía." +
                        "\nActividad:Desciende la ladera para que el monitor de te la contraseña." +
                        "\nPASS:GND2")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.skull)));
//funcionalidad del marker
        mMap.setOnMarkerClickListener(marker -> {
            mostrarDialogoActividad(marker.getTitle(), marker.getSnippet());
            return true;
        });

//funcionalidad al swotch
        switchUbicacion.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                // informo del rechazo el permiso
                Toast.makeText(this, "Permiso de ubicación no concedido", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1);
                buttonView.setChecked(false);//el boton queda desactivado hasta que de se permiso
                return;
            }
            if (mMap != null) {
                mMap.setMyLocationEnabled(isChecked);
            }
        });
        showMyLocationOnMap();
    }

    //    Muestra la localización del usuario en el mapa y mueve la cáma a esa ubicación
//                             ==============================
//                             Google Maps Zoom Levels
//                             ==============================
//                             Zoom 1   → Mundo (muy alejado)
//                             Zoom 5   → Continentes/regiones grandes
//                             Zoom 10  → Ciudad o zona amplia
//                             Zoom 15  → Calles y barrios (ideal para ubicaciones locales)
//                             Zoom 18  → Detalle de calle (coches, edificios)
//                             Zoom 21  → Máximo zoom (muy cerca, vista de edificios o interiores)
    private void showMyLocationOnMap() {
        //comprobar permiso de localizacion del usuario
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, location -> {
                        if (location != null) {
                            LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 7));
                        }
                    });
        }
    }

//metodo para mostrar la info de la localización
    private void mostrarDialogoActividad(String titulo, String prueba) {
        woah.start();
        AlertDialog.Builder builder = new AlertDialog.Builder(this); //creo cuadro
        builder.setTitle(titulo); //setteo nombre de la localizacion
        builder.setMessage(prueba); //setteo descripcion de la prueba
        final EditText input = new EditText(this); //campo de la contraseña
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);//formato de text de contraseña
        builder.setView(input); //setteo en dialogo
        //botones del cuadro
        builder.setPositiveButton("Finalizar", null);
        builder.setNegativeButton("Cancelar", null);
//funcionalidad de los botones
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(d -> { //setteo del listener
            Button finalizarBtn = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            finalizarBtn.setOnClickListener(v -> {
                String passwordIngresada = input.getText().toString().trim(); //capturo lo introducido y lo formateo
                String passwordEsperada = contraseñas.getOrDefault(titulo, ""); //lo tiro contra mi mapa de contraseñas por localizacion
//control de resultados
                if (passwordIngresada.isEmpty()) {
                    Toast.makeText(this, "Introduce una contraseña", Toast.LENGTH_SHORT).show();
                } else if (passwordIngresada.equals(passwordEsperada)) {
                    Toast.makeText(this, "¡Actividad completada!", Toast.LENGTH_SHORT).show();
                    crash.start();
                    dialog.dismiss();//cierro el dialogo
                } else {
                    Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                }
            });
        });dialog.show();
    }
}