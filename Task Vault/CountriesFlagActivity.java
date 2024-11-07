package com.jethers.reglogwdb;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class CountriesFlagActivity extends AppCompatActivity {

    private TextView countryNameTextView;
    private ImageView countryFlagImageView;
    private ListView countriesListView;

    private String[] countries = {
            "Andorra", "United Arab Emirates", "Afghanistan", "Antigua and Barbuda", "Anguilla",
            "Albania", "Armenia", "Netherlands Antilles", "Angola", "Antarctica", "Argentina",
            "American Samoa", "Austria", "Australia", "Aruba", "Åland Islands", "Azerbaijan",
            "Bosnia and Herzegovina", "Barbados", "Bangladesh", "Belgium", "Burkina Faso",
            "Bulgaria", "Bahrain", "Burundi", "Benin", "Saint Barthélemy", "Bermuda",
            "Brunei Darussalam", "Bolivia", "Bonaire, Sint Eustatius and Saba", "Brazil",
            "Bahamas", "Bhutan", "Bouvet Island", "Botswana", "Belarus", "Belize", "Canada",
            "Cocos (Keeling) Islands", "Congo, The Democratic Republic Of The", "Central African Republic",
            "Congo", "Switzerland", "Côte D'Ivoire", "Cook Islands", "Chile", "Cameroon",
            "China", "Colombia", "Costa Rica", "Cuba", "Cape Verde", "Curaçao", "Christmas Island",
            "Cyprus", "Czech Republic", "Germany", "Djibouti", "Denmark", "Dominica",
            "Dominican Republic", "Algeria", "Ecuador", "Estonia", "Egypt", "Western Sahara",
            "Eritrea", "Spain", "Ethiopia", "Finland", "Fiji", "Falkland Islands (Malvinas)",
            "Micronesia, Federated States Of", "Faroe Islands", "France", "Gabon", "United Kingdom",
            "Grenada", "Georgia", "French Guiana", "Guernsey", "Ghana", "Gibraltar", "Greenland",
            "Gambia", "Guinea", "Guadeloupe", "Equatorial Guinea", "Greece", "South Georgia and the South Sandwich Islands",
            "Guatemala", "Guam", "Guinea-Bissau", "Guyana", "Hong Kong", "Heard and McDonald Islands",
            "Honduras", "Croatia", "Haiti", "Hungary", "Indonesia", "Ireland", "Israel",
            "Isle of Man", "India", "British Indian Ocean Territory", "Iraq", "Iran, Islamic Republic Of",
            "Iceland", "Italy", "Jersey", "Jamaica", "Jordan", "Japan", "Kenya", "Kyrgyzstan",
            "Cambodia", "Kiribati", "Comoros", "Saint Kitts And Nevis", "Korea, Democratic People's Republic Of",
            "Korea, Republic of", "Kuwait", "Cayman Islands", "Kazakhstan", "Lao People's Democratic Republic",
            "Lebanon", "Saint Lucia", "Liechtenstein", "Sri Lanka", "Liberia", "Lesotho",
            "Lithuania", "Luxembourg", "Latvia", "Libya", "Morocco", "Monaco", "Moldova, Republic of",
            "Montenegro", "Saint Martin", "Madagascar", "Marshall Islands", "Macedonia, the Former Yugoslav Republic Of",
            "Mali", "Myanmar", "Mongolia", "Macao", "Northern Mariana Islands", "Martinique",
            "Mauritania", "Montserrat", "Malta", "Mauritius", "Maldives", "Malawi", "Mexico",
            "Malaysia", "Mozambique", "Namibia", "New Caledonia", "Niger", "Norfolk Island",
            "Nigeria", "Nicaragua", "Netherlands", "Norway", "Nepal", "Nauru", "New Zealand",
            "Oman", "Panama", "Peru", "French Polynesia", "Papua New Guinea", "Philippines",
            "Pakistan", "Poland", "Saint Pierre And Miquelon", "Pitcairn", "Puerto Rico", "Palestine, State of",
            "Portugal", "Palau", "Paraguay", "Qatar", "Réunion", "Romania", "Serbia", "Russian Federation",
            "Rwanda", "Saudi Arabia", "Solomon Islands", "Seychelles", "Sudan", "Sweden",
            "Singapore", "Saint Helena", "Slovenia", "Svalbard And Jan Mayen", "Slovakia", "Sierra Leone",
            "San Marino", "Senegal", "Somalia", "Suriname", "South Sudan", "Sao Tome and Principe",
            "El Salvador", "Sint Maarten", "Syrian Arab Republic", "Swaziland", "Turks and Caicos Islands",
            "Chad", "French Southern Territories", "Togo", "Thailand", "Tajikistan", "Tokelau",
            "Timor-Leste", "Turkmenistan", "Tunisia", "Tonga", "Turkey", "Trinidad and Tobago",
            "Tuvalu", "Taiwan, Republic Of China", "Tanzania, United Republic of", "Ukraine",
            "Uganda", "United States Minor Outlying Islands", "United States", "Uruguay", "Uzbekistan",
            "Holy See (Vatican City State)", "Saint Vincent And The Grenadines", "Venezuela, Bolivarian Republic of",
            "Virgin Islands, British", "Virgin Islands, U.S.", "Vietnam", "Vanuatu", "Wallis and Futuna",
            "Samoa", "Yemen", "Mayotte", "South Africa", "Zambia", "Zimbabwe"
    };

    private String[] countryCodes = {
            "AD", "AE", "AF", "AG", "AI", "AL", "AM", "AN", "AO", "AQ", "AR", "AS", "AT", "AU",
            "AW", "AX", "AZ", "BA", "BB", "BD", "BE", "BF", "BG", "BH", "BI", "BJ", "BL", "BM",
            "BN", "BO", "BQ", "BR", "BS", "BT", "BV", "BW", "BY", "BZ", "CA", "CC", "CD", "CF",
            "CG", "CH", "CI", "CK", "CL", "CM", "CN", "CO", "CR", "CU", "CV", "CW", "CX", "CY",
            "CZ", "DE", "DJ", "DK", "DM", "DO", "DZ", "EC", "EE", "EG", "EH", "ER", "ES", "ET",
            "FI", "FJ", "FK", "FM", "FO", "FR", "GA", "GB", "GD", "GE", "GF", "GG", "GH", "GI",
            "GL", "GM", "GN", "GP", "GQ", "GR", "GS", "GT", "GU", "GW", "GY", "HK", "HM", "HN",
            "HR", "HT", "HU", "ID", "IE", "IL", "IM", "IN", "IO", "IQ", "IR", "IS", "IT", "JE",
            "JM", "JO", "JP", "KE", "KG", "KH", "KI", "KM", "KN", "KP", "KR", "KW", "KY", "KZ",
            "LA", "LB", "LC", "LI", "LK", "LR", "LS", "LT", "LU", "LV", "LY", "MA", "MC", "MD",
            "ME", "MF", "MG", "MH", "MK", "ML", "MM", "MN", "MO", "MP", "MQ", "MR", "MS", "MT",
            "MU", "MV", "MW", "MX", "MY", "MZ", "NA", "NC", "NE", "NF", "NG", "NI", "NL", "NO",
            "NP", "NR", "NZ", "OM", "PA", "PE", "PF", "PG", "PH", "PK", "PL", "PM", "PN", "PR",
            "PS", "PT", "PW", "PY", "QA", "RE", "RO", "RS", "RU", "RW", "SA", "SB", "SC", "SD",
            "SE", "SG", "SH", "SI", "SJ", "SK", "SL", "SM", "SN", "SO", "SR", "SS", "ST", "SV",
            "SX", "SY", "SZ", "TC", "TD", "TF", "TG", "TH", "TJ", "TK", "TL", "TM", "TN", "TO",
            "TR", "TT", "TV", "TW", "TZ", "UA", "UG", "UM", "US", "UY", "UZ", "VA", "VC", "VE",
            "VG", "VI", "VN", "VU", "WF", "WS", "YE", "YT", "ZA", "ZM", "ZW"
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries_flag);

        countryNameTextView = findViewById(R.id.countryName);
        countryFlagImageView = findViewById(R.id.countryFlag);
        countriesListView = findViewById(R.id.countriesListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countries);
        countriesListView.setAdapter(adapter);

        countriesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCountry = countries[position];
                String countryCode = countryCodes[position];
                fetchAndDisplayFlag(selectedCountry, countryCode);
            }
        });
    }

    private void fetchAndDisplayFlag(String countryName, String countryCode) {

        String flagUrl = "https://flagsapi.com/" + countryCode + "/shiny/64.png";

        countryNameTextView.setText(countryName);
        countryNameTextView.setVisibility(View.VISIBLE);

        Glide.with(this)
                .load(flagUrl)
                .into(countryFlagImageView);

        countryFlagImageView.setVisibility(View.VISIBLE);
    }
}
