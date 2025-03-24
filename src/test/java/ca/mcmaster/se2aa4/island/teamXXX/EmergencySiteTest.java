import ca.mcmaster.se2aa4.island.teamXXX.EmergencySite;
import ca.mcmaster.se2aa4.island.teamXXX.Creeks;
import ca.mcmaster.se2aa4.island.teamXXX.Creek;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class EmergencySiteTest {

    @Mock
    private Creeks mockCreeks;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetNearestCreek() {
        EmergencySite site = new EmergencySite(3, 5);
        Creek mockCreek = new Creek("creek-123");

        when(mockCreeks.getNearestCreek(3, 5)).thenReturn(mockCreek);

        String result = site.getNearestCreek(mockCreeks);
        assertEquals("creek-123", result);
    }
}