package sla.org.movie;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Movie {
    private String[] title = new String[10];
    private String[] rating = new String[10];
    private String[] genre = new String[10];
    private String[] runTime = new String[10];
    private String[] synoposis = new String[10];
    private String[] image = new String[10];

    private String api_key = "c16cb2f114a9e49c24942d6f9590e531";
    private String api_url = "https://api.themoviedb.org/3/movie/now_playing?api_key=" + api_key + "&language=en-US&page=1";

    Movie(Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, api_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            JSONObject res = new JSONObject(response);
                            JSONArray obj = res.getJSONArray("results");
                            JSONObject content = obj.getJSONObject(0);
                            title[0] = content.getString("title");
                            rating[0] = content.getString("adult"); // Todo: Get more accurate rating

                            JSONArray genreOBJ = content.getJSONArray("genre_ids");
                            JSONObject genreContent = genreOBJ.getJSONObject(0);
                            String genreId = genreContent.getString("genre_ids");
                            genre[0] = getGenre(genreId);


                            runTime[0] = content.getString("adult"); // Todo: Get runtime
                            synoposis[0] = content.getString("overview");
                            image[0] = "https://image.tmdb.org/t/p/original" + content.getString("poster_path");

                            System.out.println(title[0]);
                            System.out.println(rating[0]);
                            System.out.println(genre[0]);
                            System.out.println(runTime[0]);
                            System.out.println(synoposis[0]);
                            System.out.println(image[0]);

                        } catch (JSONException error) {
                            error.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    String getGenre(String id) { // Todo: FINISH!
        if (id.equals("28")) {
            return "Action";
        } else if (id.equals("12")) {
            return "Adventure";
        } else if (id.equals("16")) {
            return "Animation";
        } else if (id.equals("35")) {
            return "Comedy";
        } else if (id.equals("80")) {
            return "Crime";
        } else if (id.equals("99")) {
            return "Documentary";
        } else if (id.equals("18")) {
            return "Drama";
        } else if (id.equals("10751")) {
            return "Family";
        } else if (id.equals("14")) {
            return "Fantasy";
        } else if (id.equals("36")) {
            return "History";
        } else if (id.equals("27")) {
            return "Horror";
        } else if (id.equals("10751")) {
            return "Family";
        } else if (id.equals("14")) {
            return "Fantasy";
        } else if (id.equals("36")) {
            return "History";
        } else if (id.equals("27")) {
            return "Horror";
        } else if (id.equals("10402")) {
            return "Music";
        } else if (id.equals("9648")) {
            return "Mystery";
        } else if (id.equals("10749")) {
            return "Romance";
        } else if (id.equals("878")) {
            return "Sci-Fi";
        } else if (id.equals("10770")) {
            return "TV Movie";
        } else if (id.equals("53")) {
            return "Thriller";
        } else if (id.equals("10752")) {
            return "War";
        } else if (id.equals("37")) {
            return "Western";
        } else {
            return "Unknown";
        }
    }

    String title(int item) {
        return title[item];
    }

    String subTitle(int item) {
        return rating[item] + " | " + genre[item] + " | " + runTime[item];
    }

    String description(int item) {
        return synoposis[item];
    }

    String image(int item) {
        return image[item]; // Need to figure out how this works.
    }

}
